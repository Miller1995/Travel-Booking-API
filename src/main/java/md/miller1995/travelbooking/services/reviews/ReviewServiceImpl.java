package md.miller1995.travelbooking.services.reviews;


import lombok.extern.slf4j.Slf4j;
import md.miller1995.travelbooking.exceptions.reviews.ReviewNotFoundException;
import md.miller1995.travelbooking.models.dtos.reviews.ReviewDTO;
import md.miller1995.travelbooking.models.entities.reviews.ReviewEntity;
import md.miller1995.travelbooking.repositories.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        var reviewDTOToBeSave = ReviewDTO.builder()
                .review(reviewDTO.getReview())
                .place(reviewDTO.getPlace())
                .rating(reviewDTO.getRating())
                .build();

        ReviewEntity reviewEntityToBeSaved = convertReviewDTOToEntity(reviewDTOToBeSave);
        reviewEntityToBeSaved.setCreatedAt(convertLocalDateToDateSQL(LocalDate.now()));

        ReviewEntity savedReview = reviewRepository.save(reviewEntityToBeSaved);
        ReviewDTO returnReview = convertReviewEntityToDTO(savedReview);
        return returnReview;
    }

    @Override
    @Transactional
    public void deleteReviewById(UUID id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewDTO getReviewsById(UUID id) {
        var foundReview = reviewRepository.findReviewEntitiesById(id).orElseThrow(ReviewNotFoundException::new);
        ReviewDTO reviewDTO = convertReviewEntityToDTO(foundReview);
        return reviewDTO;
    }

    @Override
    public List<ReviewDTO> getAllReviewsByPlace(String place) {
         List<ReviewEntity> reviewEntityList = reviewRepository.findReviewEntitiesByPlace(place);
         List<ReviewDTO> reviewDTOList = convertListReviewEntityToDTO(reviewEntityList);
         return reviewDTOList;
    }

    private Date convertLocalDateToDateSQL(LocalDate localDate){
        Date date = Date.valueOf(localDate);
        return date;
    }

    private ReviewDTO convertReviewEntityToDTO(ReviewEntity reviewEntity){
        return modelMapper.map(reviewEntity, ReviewDTO.class);
    }

    private ReviewEntity convertReviewDTOToEntity(ReviewDTO reviewDTO){
        return modelMapper.map(reviewDTO, ReviewEntity.class);
    }

    private List<ReviewDTO> convertListReviewEntityToDTO(List<ReviewEntity> reviewEntityList){
        return reviewEntityList.stream()
                .map(this::convertReviewEntityToDTO)
                .collect(Collectors.toList());
    }
}
