package md.miller1995.travelbooking.services.travels;

import lombok.extern.slf4j.Slf4j;
import md.miller1995.travelbooking.models.dtos.travels.TravelDTO;
import md.miller1995.travelbooking.models.entities.travels.TravelEntity;
import md.miller1995.travelbooking.repositories.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
@Transactional(readOnly = true)
public class TravelServiceImpl implements TravelService{

    private final TravelRepository travelRepository;

    @Autowired
    public TravelServiceImpl(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SUPER_ADMIN')")
    public TravelDTO saveTravel(TravelDTO travelDTO) {
        var travel = TravelDTO.builder()
                .type(travelDTO.getType())
                .startDate(travelDTO.getStartDate())
                .endDate(travelDTO.getEndDate())
                .amount(travelDTO.getAmount())
                .build();

        TravelEntity travelEntity = convertTravelDTOTOTravelEntity(travel);
        TravelEntity savedTravel = travelRepository.save(travelEntity);
        TravelDTO returnDTO = convertTravelEntityToTravelDTO(savedTravel);
        log.info("Travel ");
        return returnDTO;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SUPER_ADMIN')")
    public TravelDTO updateTravel(UUID id, TravelDTO travelDTO) {
        var travelResult = travelRepository.findById(id);
        var travelToBeUpdate = travelResult.get();

        travelToBeUpdate.setType(travelDTO.getType());
        travelToBeUpdate.setStartDate(convertLocalDateToDateSQL(travelDTO.getStartDate()));
        travelToBeUpdate.setEndDate(convertLocalDateToDateSQL(travelDTO.getEndDate()));
        travelToBeUpdate.setAmount(travelDTO.getAmount());

        TravelEntity updatedTravel = travelRepository.save(travelToBeUpdate);
        TravelDTO returnTravel = convertTravelEntityToTravelDTO(updatedTravel);
        return returnTravel;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SUPER_ADMIN')")
    public void deleteTravel(UUID id) {
        travelRepository.deleteById(id);
    }

    @Override
    public List<TravelDTO> findAllTravelByType(String type) {
        List<TravelEntity> travelEntityList = travelRepository.findTravelEntitiesByType(type);
        List<TravelDTO> travelDTOList = convertTravelEntityListToTravelDTOList(travelEntityList);

        return  travelDTOList;
    }

    @Override
    public List<TravelDTO> findAllTravelByDateAndByType(String type, LocalDate startDate, LocalDate endDate) {
       List<TravelEntity> travelEntityList = travelRepository.findAllByTypeAndStartDateIsGreaterThanEqualAndEndDateLessThanEqual(type,startDate,endDate);
       List<TravelDTO> travelDTOList = convertTravelEntityListToTravelDTOList(travelEntityList);
       return travelDTOList;
    }

    private List<TravelDTO> convertTravelEntityListToTravelDTOList(List<TravelEntity> travelEntityList) {
        return  travelEntityList
                .stream()
                .map(this::convertTravelEntityToTravelDTO)
                .collect(Collectors.toList());

    }

    private TravelEntity convertTravelDTOTOTravelEntity(TravelDTO travelDTO){
        TravelEntity travelEntity = new TravelEntity();
        travelEntity.setType(travelDTO.getType());
        travelEntity.setStartDate(convertLocalDateToDateSQL(travelDTO.getStartDate()));
        travelEntity.setEndDate(convertLocalDateToDateSQL(travelDTO.getEndDate()));
        travelEntity.setAmount(travelDTO.getAmount());

        return travelEntity;
    }

    private TravelDTO convertTravelEntityToTravelDTO(TravelEntity travelEntity){
        TravelDTO travelDTO = new TravelDTO();
        travelDTO.setType(travelEntity.getType());
        travelDTO.setStartDate(travelEntity.getStartDate().toLocalDate());
        travelDTO.setEndDate(travelEntity.getEndDate().toLocalDate());
        travelDTO.setAmount(travelEntity.getAmount());
        return  travelDTO;
    }

    private Date convertLocalDateToDateSQL(LocalDate localDate){
        Date date = Date.valueOf(localDate);
        return date;
    }
}
