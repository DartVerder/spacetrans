package com.company.st.service;

import com.company.st.entity.Planet;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CsvService {
    String NAME = "st_CsvService";

    List <Planet> getPlanets(File file) throws IOException;

}