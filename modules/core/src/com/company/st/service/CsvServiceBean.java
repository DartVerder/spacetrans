package com.company.st.service;

import com.haulmont.cuba.core.global.FileStorageException;
import org.springframework.stereotype.Service;

import com.company.st.entity.Planet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service(CsvService.NAME)
public class CsvServiceBean implements CsvService {
    public static final String NAME = "st_CsvBean";


    @Transactional
    @Override
    public List <Planet> getPlanets(File file) throws IOException{

        List<Planet> planetList = new ArrayList<>();
        ICsvBeanReader csvBeanReader = new CsvBeanReader(new FileReader(file.getPath()), CsvPreference.STANDARD_PREFERENCE);

        // указываем как будем мапить
        String[] mapping = new String[]{ "name", "Mass", "semiMajorAxis", "orbitalPeriod", "rotationPeriod", "Rings"};

        // получаем обработчики
        CellProcessor[] procs = getProcessors();
        Planet planet;
        // обходим весь csv файлик до конца
        csvBeanReader.getHeader(true);
        while ((planet = csvBeanReader.read(Planet.class, mapping, procs)) != null) {
            planetList.add(planet);
        }
        csvBeanReader.close();
        return planetList;
    }

    /**
     * Задаем обработчики ячеек
     */
    private static CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),

        };
    }
}

