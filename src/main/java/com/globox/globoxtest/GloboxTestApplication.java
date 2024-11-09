package com.globox.globoxtest;

import com.globox.globoxtest.services.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GloboxTestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GloboxTestApplication.class, args);
    }

    /**
     *
    There are some improvement That I will mention, and  I believe with
     that improvement the api will work faster and program will have better performance.

     -- if we want database structure like original one we can use graphQL instead of rest to better performance.
     -- it's better for some filed like genre we can use enum and some like title type we can use relational table,
     -- use pagination is faster than get all data once
     -- use some cashing system design is helpful for some api like get all data make it faster
     -- by database indexing and sharding we can make it faster
     -- I used native query  because without changing anything in database, it was faster to get data, but
        we can use procedure in database side which make it faster
     -- I personally like to used ResultDto to pass data to frontend make it consistency.
     -- we can used file path in yaml file to read csv files
     -- I used postgresql we could consider other database for performance
     -- is better to write test
    */


    @Autowired
    private DataImportService dataImportService;

    @Override
    public void run(String... args) {
        //dataImportService.importData();
    }
}
