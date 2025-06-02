package com.pm.patient_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.pm.patient_service.grpc.BillingServiceGrpcClient;
import com.pm.patient_service.kafka.kafkaProducer;
import org.springframework.stereotype.Service;

import com.pm.patient_service.repository.PatientRepository;
import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.exception.EmailAlreadyExistsException;
import com.pm.patient_service.exception.PatientNotFoundException;



@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final kafkaProducer kafkaProducer;

    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient, kafkaProducer kafkaProducer) {
        this.patientRepository=patientRepository;
        this.billingServiceGrpcClient=billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients=patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email"+"already exists"+patientRequestDTO.getEmail());
        }

        Patient newPatient=patientRepository.save(
            PatientMapper.toPatient(patientRequestDTO)
        );
        billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(),newPatient.getName(),newPatient.getEmail());
        kafkaProducer.sendEvent(newPatient);
        return PatientMapper.toDTO(newPatient);

    }

    public PatientResponseDTO updatePatient(UUID id,PatientRequestDTO patientRequestDTO ){
        Patient patient=patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient Not Found with ID: " + id));
    
        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
            throw new EmailAlreadyExistsException("A patient with this email"+"already exists"+patientRequestDTO.getEmail());
        }
        
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateofBirth(LocalDate.parse(patientRequestDTO.getDateofBirth()));

        Patient updatedPatient=patientRepository.save(patient);
        billingServiceGrpcClient.updateBillingAccount(
                updatedPatient.getId().toString(),
                updatedPatient.getName(),
                updatedPatient.getEmail()
        );
        return PatientMapper.toDTO(updatedPatient);


    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }


    
}
