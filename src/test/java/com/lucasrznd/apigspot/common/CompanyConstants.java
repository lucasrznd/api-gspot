package com.lucasrznd.apigspot.common;

import com.lucasrznd.apigspot.dtos.CompanyDTO;
import com.lucasrznd.apigspot.models.CompanyModel;

import java.util.Arrays;
import java.util.List;

public class CompanyConstants {

    public static final CompanyModel COMPANY = new CompanyModel(null, "Molinis Supermercados", "4325255252", "");
    public static final CompanyModel MAGAZINE_LUIZA = new CompanyModel(2L, "Magazine Luiza", "4315155151", "");

    public static final CompanyDTO COMPANY_DTO = new CompanyDTO(null, "Molinis Supermercados", "4325255252", "");
    public static final CompanyDTO MAGAZINE_LUIZA_DTO = new CompanyDTO(null, "Magazine Luiza", "4315155151", "");
    public static final CompanyDTO INVALID_COMPANY_DTO = new CompanyDTO(null, "", "", "");
    public static final CompanyDTO NULL_COMPANY_DTO = new CompanyDTO(null, null, null, null);

    public static final List<CompanyDTO> COMPANIES_DTO_LIST = Arrays.asList(COMPANY_DTO, MAGAZINE_LUIZA_DTO);

}
