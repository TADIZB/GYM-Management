package com.example.backend.services.implement;


import com.example.backend.dtos.EquipmentDTO;
import com.example.backend.models.Equipment;
import com.example.backend.repositories.EquipmentRepository;
import com.example.backend.repositories.RoomRepository;
import com.example.backend.services.EquipmentService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EquipmentServiceImpl implements EquipmentService {

    EquipmentRepository equipmentRepository;
    RoomRepository roomRepository;

    @Override
    public Page<EquipmentDTO> getAllEquipment(Pageable pageable) {
        return equipmentRepository.findAll(pageable).map(EquipmentDTO::fromEntity);
    }

    @Override
    public EquipmentDTO getEquipmentById(Long id) {
        return EquipmentDTO.fromEntity(Objects.requireNonNull(equipmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Equipment not found with id: " + id))));
    }

    @Override
    public String addEquipment(JsonNode equipmentData) {


        Equipment equipment = new Equipment();
        equipment.setEquipmentName(equipmentData.get("equipment_name").asText());
        equipment.setOrigin(equipmentData.get("origin").asText());
        equipment.setWarranty(equipmentData.get("warranty").asText());
        equipment.setImportDate(LocalDate.parse(equipmentData.get("import_date").asText()));
        equipment.setStatus(equipmentData.get("status").asText());
        equipment.setRoom(roomRepository.getReferenceById(equipmentData.get("room_id").asLong()));
        equipmentRepository.save(equipment);
        return "Add Successfully";
    }

    @Override
    public String updateEquipment(JsonNode equipmentData, Long id) {
        equipmentRepository.findById(id).ifPresent(equipment -> {
            equipment.setEquipmentName(equipmentData.get("equipment_name").asText());
            equipment.setOrigin(equipmentData.get("origin").asText());
            equipment.setWarranty(equipmentData.get("warranty").asText());
            equipment.setImportDate(LocalDate.parse(equipmentData.get("import_date").asText()));
            equipment.setStatus(equipmentData.get("status").asText());
            equipment.setRoom(roomRepository.getReferenceById(equipmentData.get("room_id").asLong()));
            equipmentRepository.save(equipment);
        });
        return "Update Successfully";
    }

    @Override
    public String deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
        return "Delete Successfully";
    }
}
