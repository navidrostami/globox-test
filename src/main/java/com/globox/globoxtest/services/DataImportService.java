package com.globox.globoxtest.services;

import com.globox.globoxtest.dal.entities.*;
import com.globox.globoxtest.dal.repositories.*;
import com.globox.globoxtest.util.CustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;


@Service
public class DataImportService {

    @Autowired
    private NameRepository nameRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private TitlePrincipalRepository titlePrincipalRepository;
    @Autowired
    private TitleRatingRepository titleRatingRepository;
    @Autowired
    private TitleCrewRepository titleCrewRepository;

    public void importData() {
        importNames();
        importTitleCrew();
        importTitlePrincipals();
        importTitleRatings();
        importTitles();
    }

    private void importNames() {
        String fileName = "/home/navid/Desktop/IMDB_files/name.basics.tsv.gz"; // Update with the actual path
        try (
                InputStream fileStream = new FileInputStream(fileName);
                InputStream gzipStream = new GZIPInputStream(fileStream);
                Reader decoder = new InputStreamReader(gzipStream, StandardCharsets.UTF_8);
                BufferedReader buffered = new BufferedReader(decoder)
        ) {
            String line;
            // Skip the header line
            buffered.readLine();

            List<Name> namesBatch = new ArrayList<>();
            int batchSize = 100000;

            while ((line = buffered.readLine()) != null) {
                String[] fields = line.split("\t");

                if (fields.length == 6) {
                    Name name = new Name();
                    name.setNconst(fields[0]);
                    name.setPrimaryName(CustomUtils.parseList(fields[1]));
                    name.setBirthYear(CustomUtils.parseInt(fields[2]));
                    name.setDeathYear(CustomUtils.parseInt(fields[3]));
                    name.setPrimaryProfession(CustomUtils.parseList(fields[4]));
                    name.setKnownForTitles(CustomUtils.parseList(fields[5]));

                    namesBatch.add(name);

                    // Save in batches to improve performance
                    if (namesBatch.size() >= batchSize) {
                        nameRepository.saveAll(namesBatch);
                        namesBatch.clear();
                    }
                }
            }

            // Save any remaining names
            if (!namesBatch.isEmpty()) {
                nameRepository.saveAll(namesBatch);
            }

            System.out.println("Names import completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importTitleCrew() {
        String fileName = "/home/navid/Desktop/IMDB_files/title.crew.tsv.gz"; // Update with the actual path
        try (
                InputStream fileStream = new FileInputStream(fileName);
                InputStream gzipStream = new GZIPInputStream(fileStream);
                Reader decoder = new InputStreamReader(gzipStream, StandardCharsets.UTF_8);
                BufferedReader buffered = new BufferedReader(decoder)
        ) {
            String line;
            // Skip the header line
            buffered.readLine();

            List<TitleCrew> tempBatch = new ArrayList<>();
            int batchSize = 100000;

            while ((line = buffered.readLine()) != null) {
                String[] fields = line.split("\t");

                if (fields.length == 3) {
                    TitleCrew titleCrew = new TitleCrew();

                    titleCrew.setNconst(fields[0]);
                    titleCrew.setDirectors(CustomUtils.parseList(fields[1]));
                    titleCrew.setWriters(CustomUtils.parseList(fields[2]));

                    tempBatch.add(titleCrew);

                    // Save in batches to improve performance
                    if (tempBatch.size() >= batchSize) {
                        titleCrewRepository.saveAll(tempBatch);
                        tempBatch.clear();
                    }
                }
            }

            // Save any remaining names
            if (!tempBatch.isEmpty()) {
                titleCrewRepository.saveAll(tempBatch);
            }

            System.out.println("TitleCrew import completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void importTitlePrincipals() {
        String fileName = "/home/navid/Desktop/IMDB_files/title.principals.tsv.gz"; // Update with the actual path
        try (
                InputStream fileStream = new FileInputStream(fileName);
                InputStream gzipStream = new GZIPInputStream(fileStream);
                Reader decoder = new InputStreamReader(gzipStream, StandardCharsets.UTF_8);
                BufferedReader buffered = new BufferedReader(decoder)
        ) {
            String line;
            // Skip the header line
            buffered.readLine();

            List<TitlePrincipal> tempBatch = new ArrayList<>();
            int batchSize = 100000;

            while ((line = buffered.readLine()) != null) {
                String[] fields = line.split("\t");

                if (fields.length == 6) {
                    TitlePrincipal titlePrincipal = new TitlePrincipal();
                    titlePrincipal.setTconst(CustomUtils.parseList(fields[0]));
                    titlePrincipal.setOrdering(CustomUtils.parseInt(fields[1]));
                    titlePrincipal.setNconst(CustomUtils.parseList(fields[2]));
                    titlePrincipal.setCategory(CustomUtils.parseList(fields[3]));
                    titlePrincipal.setJob(CustomUtils.parseList(fields[4]));
                    titlePrincipal.setCharacters(CustomUtils.parseList(fields[5]));

                    tempBatch.add(titlePrincipal);

                    // Save in batches to improve performance
                    if (tempBatch.size() >= batchSize) {
                        titlePrincipalRepository.saveAll(tempBatch);
                        tempBatch.clear();
                    }
                }
            }

            // Save any remaining names
            if (!tempBatch.isEmpty()) {
                titlePrincipalRepository.saveAll(tempBatch);
            }

            System.out.println("Title principal import completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }    }

    private void importTitleRatings() {
        String fileName = "/home/navid/Desktop/IMDB_files/title.ratings.tsv.gz"; // Update with the actual path
        try (
                InputStream fileStream = new FileInputStream(fileName);
                InputStream gzipStream = new GZIPInputStream(fileStream);
                Reader decoder = new InputStreamReader(gzipStream, StandardCharsets.UTF_8);
                BufferedReader buffered = new BufferedReader(decoder)
        ) {
            String line;
            // Skip the header line
            buffered.readLine();

            List<TitleRating> tempBatch = new ArrayList<>();
            int batchSize = 100000;

            while ((line = buffered.readLine()) != null) {
                String[] fields = line.split("\t");

                if (fields.length == 3) {
                    TitleRating titleRating = new TitleRating();

                    titleRating.setTconst(fields[0]);
                    titleRating.setAverageRating(CustomUtils.parseDouble(fields[1]));
                    titleRating.setNumVotes(CustomUtils.parseInt(fields[2]));

                    tempBatch.add(titleRating);

                    // Save in batches to improve performance
                    if (tempBatch.size() >= batchSize) {
                        titleRatingRepository.saveAll(tempBatch);
                        tempBatch.clear();
                    }
                }
            }

            // Save any remaining names
            if (!tempBatch.isEmpty()) {
                titleRatingRepository.saveAll(tempBatch);
            }

            System.out.println("TitleRating import completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void importTitles() {
        String fileName = "/home/navid/Desktop/IMDB_files/title.basics.tsv.gz"; // Update with the actual path
        try (
                InputStream fileStream = new FileInputStream(fileName);
                InputStream gzipStream = new GZIPInputStream(fileStream);
                Reader decoder = new InputStreamReader(gzipStream, StandardCharsets.UTF_8);
                BufferedReader buffered = new BufferedReader(decoder)
        ) {
            String line;
            buffered.readLine();

            List<Title> tempBatch = new ArrayList<>();
            int batchSize = 1000000;

            while ((line = buffered.readLine()) != null) {
                String[] fields = line.split("\t");

                if (fields.length == 9) {
                    Title title = new Title();
                    title.setTconst(fields[0]);
                    title.setTitleType(CustomUtils.parseList(fields[1]));
                    title.setPrimaryTitle(CustomUtils.parseList(fields[2]));
                    title.setOriginalTitle(CustomUtils.parseList(fields[3]));
                    title.setIsAdult(CustomUtils.parseInt(fields[4]));
                    title.setStartYear(CustomUtils.parseInt(fields[5]));
                    title.setEndYear(CustomUtils.parseInt(fields[6]));
                    title.setRuntimeMinutes(CustomUtils.parseInt(fields[7]));
                    title.setGenres(CustomUtils.parseList(fields[8]));
                    tempBatch.add(title);

                    // Save in batches to improve performance
                    if (tempBatch.size() >= batchSize) {
                        titleRepository.saveAll(tempBatch);
                        tempBatch.clear();
                    }
                }
            }

            // Save any remaining names
            if (!tempBatch.isEmpty()) {
                titleRepository.saveAll(tempBatch);
            }

            System.out.println("Title import completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
