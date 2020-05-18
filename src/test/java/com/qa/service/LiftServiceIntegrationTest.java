package com.qa.service;

import com.qa.domain.Lift;
import com.qa.dto.LiftDTO;
import com.qa.repo.LiftsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LiftServiceIntegrationTest {

    @Autowired
    private LiftService service;

    @Autowired
    private LiftsRepository repository;

    @Autowired
    private ModelMapper mapper;

    private Lift testLift;

    private Lift testLiftWithID;

    private LiftDTO mapToDTO(Lift lift){
        return this.mapper.map(lift, LiftDTO.class);
    }

//    @Before
//    public void setUp(){
//        this.testLift = new Lift("My title", "my description");
//        this.repository.deleteAll();
//        this.testLiftWithID = this.repository.save(this.testLift);
//    }

    @Test
    public void readLiftsTest(){
        assertThat(this.service.readLifts())
        .isEqualTo(
                Stream.of(this.mapToDTO(testLiftWithID)).collect(Collectors.toList())
        );
    }

    @Test
    public void createLiftTest(){
        assertEquals(this.mapToDTO(this.testLiftWithID), this.service.createLift(testLift));
    }

    @Test
    public void findLiftByIdTest(){
        assertThat(this.service.findLiftById(this.testLiftWithID.getId())).isEqualTo(this.mapToDTO(this.testLiftWithID));
    }


//    @Test
//    public void updateLiftTest(){
//        Lift newLift = new Lift("Favourite movies", "The grinch");
//        Lift updateLift = new Lift(newLift.getTitle(), newLift.getDescription());
//        updateLift.setId(this.testLiftWithID.getId());
//        assertThat(this.service.updateLift(this.testLiftWithID.getId() ,newLift))
//                .isEqualTo(this.mapToDTO(updateLift));
//    }

    @Test
    public void deleteLiftTest(){
        assertThat(this.service.deleteLift(this.testLiftWithID.getId())).isFalse();
    }


}
