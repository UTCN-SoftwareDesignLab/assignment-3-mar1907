package hospital.service.patient;

import hospital.dto.PatientDTO;
import hospital.entity.Patient;
import hospital.repository.patient.PatientRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceImplTest {

    @Mock
    PatientRepository patientRepository;

    @InjectMocks
    PatientServiceImpl patientService;

    Patient patient;

    @Before
    public void setUp() throws Exception {
        patient = new Patient("name","MS123456","1234567890123",new Date(),"alabala");
    }

    @Test
    public void getAll() {
        when(patientRepository.findAll()).thenReturn(new ArrayList<>());
        Assert.assertEquals(patientService.getAll().size(),0);
    }

    @Test
    public void save() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.name = patient.getName();
        patientDTO.idCard = patient.getIdCard();
        patientDTO.cnp = patient.getCnp();
        patientDTO.dob = patient.getDob();
        patientDTO.address = patient.getAddress();
        Assert.assertEquals(patientService.save(patientDTO).getResult(),Boolean.TRUE);
    }

    @Test
    public void update() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.name = patient.getName();
        patientDTO.idCard = patient.getIdCard();
        patientDTO.cnp = patient.getCnp();
        patientDTO.dob = patient.getDob();
        patientDTO.address = patient.getAddress();
        Assert.assertEquals(patientService.update(patientDTO,1).getResult(),Boolean.TRUE);
    }
}