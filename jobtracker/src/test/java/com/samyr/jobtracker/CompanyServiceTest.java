package com.samyr.jobtracker;

import com.samyr.jobtracker.exception.CompanyNotFoundException;
import com.samyr.jobtracker.model.Company;
import com.samyr.jobtracker.repositories.CompanyRepository;
import com.samyr.jobtracker.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    public void shouldReturnACompanyById(){

        // ARRANGE
        Company company = new Company();
        company.setName("Microsoft");

        when(companyRepository.findById(1))
                .thenReturn(Optional.of(company));

        // ACT
        Company result = companyService.getCompanyById(1);

        // ASSERT
        assertNotNull(result);
        assertEquals("Microsoft", result.getName());
    }

    @Test
    public void shouldReturnCompanyNotFoundException(){
        // ARRANGE
        when(companyRepository.findById(9999))
                .thenReturn(Optional.empty());

        // ASSERT
        assertThrows(CompanyNotFoundException.class,
                () -> companyService.getCompanyById(9999));
    }

    @Test
    public void shouldCreateCompany(){

        // ARRANGE
        Company company = new Company();
        company.setName("Microsoft");
        company.setSite("https://microsoft.com");
        company.setLocalization("Lisboa");

        when(companyRepository.save(company))
                .thenReturn(company);
        // ACT
        Company result = companyService.createCompany(company);

        // ASSERT
        assertEquals(company, result);
        assertEquals("Microsoft", result.getName());
        verify(companyRepository).save(company);
    }

    @Test
    public void shouldReturnListOfCompanies(){

        // ARRANGE
        List<Company> companyList = new ArrayList<>();
        Company company = new Company();
        company.setName("Microsoft");
        company.setSite("https://microsoft.com");
        company.setLocalization("Lisboa");

        Company company2 = new Company();
        company2.setName("Meta");
        company2.setSite("https://metaIA.com");
        company2.setLocalization("Porto");

        companyList.add(company);
        companyList.add(company2);

        when(companyRepository.findAll())
                .thenReturn(companyList);

        // ACT
        List<Company> result = companyService.companiesList();

        // ASSERT
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Microsoft", result.get(0).getName());
        assertEquals("Meta", result.get(1).getName());
        verify(companyRepository).findAll();
    }

    @Test
    public void shouldDeleteCompany(){
        // ARRANGE
        Company company = new Company();
        company.setId(1);
        company.setName("Microsoft");
        company.setSite("https://microsoft.com");
        company.setLocalization("Lisboa");

        when(companyRepository.findById(1))
                .thenReturn(Optional.of(company));


        // ACT
        companyService.deleteCompanyById(1);

        // ASSERT
        verify(companyRepository).delete(company);
    }

    @Test
    public void shouldUpdateCompany(){
        // ARRANGE
        Company company = new Company();
        company.setId(1);
        company.setName("Microsoft");
        company.setSite("https://microsoft.com");
        company.setLocalization("Lisboa");

        Company company2 = new Company();
        company2.setName("Meta");
        company2.setSite("https://metaIA.com");
        company2.setLocalization("Porto");

        when(companyRepository.findById(company.getId()))
                .thenReturn(Optional.of(company));

        when(companyRepository.save(company))
                .thenReturn(company);

        // ACT
        Company result = companyService.updateCompanyById(1, company2);

        // ASSERT
        assertEquals(company2.getName(), result.getName());
        assertEquals(1, result.getId());
        verify(companyRepository).findById(1);
        verify(companyRepository).save(company);

    }

    @Test
    public void shouldUpdateOnlyLocation(){
        // ARRANGE
        Company company = new Company();
        company.setId(1);
        company.setName("Microsoft");
        company.setSite("https://microsoft.com");
        company.setLocalization("Lisboa");

        Company company2 = new Company();
        company2.setLocalization("Porto");

        when(companyRepository.findById(company.getId()))
                .thenReturn(Optional.of(company));

        when(companyRepository.save(company))
                .thenReturn(company);


        // ACT
        Company result = companyService.updateCompanyById(1, company2);

        // ASSERT
        assertEquals("Microsoft", result.getName());
        assertEquals("https://microsoft.com", result.getSite());
        assertEquals("Porto", result.getLocalization());
        verify(companyRepository).findById(1);
        verify(companyRepository).save(company);
    }

    @Test
    public void shouldThrowCompanyNotFoundWhenUpdating(){
        // ARRANGE
        Company company = new Company();
        company.setName("Microsoft");

        when(companyRepository.findById(9999))
                .thenReturn(Optional.empty());

        // ASSERT
        assertThrows(CompanyNotFoundException.class,
                () -> companyService.updateCompanyById(9999, company));

        verify(companyRepository).findById(9999);
        verify(companyRepository, never()).save(any());
    }

    @Test
    public void validateCompanyShouldThrowIllegalArgumentExceptionWhenNull(){
        // ARRANGE
        Company company = null;

        // ACT
        assertThrows(IllegalArgumentException.class,
                () -> companyService.validateCompany(company));

        verifyNoInteractions(companyRepository);
    }

    @Test
    public void validateCompanyShouldReturnCompanyWhenExists (){
        // ARRANGE
        Company company = new Company();
        company.setId(1);
        company.setName("Microsoft");
        company.setSite("https://microsoft.com");
        company.setLocalization("Lisboa");

        when(companyRepository.findById(1))
                .thenReturn(Optional.of(company));

        // ACT
        Company result = companyService.validateCompany(company);

        // ASSERT
        assertEquals("Microsoft", result.getName());
        verify(companyRepository).findById(1);
    }

    @Test
    public void shouldThrowCompanyNotFoundIfNotExists(){

    }

}
