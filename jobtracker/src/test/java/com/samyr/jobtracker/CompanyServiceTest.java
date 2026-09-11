package com.samyr.jobtracker;

import com.samyr.jobtracker.model.Company;
import com.samyr.jobtracker.repositories.CompanyRepository;
import com.samyr.jobtracker.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    public void shouldReturnACompany(){

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
}
