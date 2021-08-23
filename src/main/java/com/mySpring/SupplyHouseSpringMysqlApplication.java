package com.mySpring;

import java.io.File;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mySpring.repository.SupplierProductRepository;
import com.mySpring.service.SupplierProductService;
import com.mySpring.files.MyFileReader;

@SpringBootApplication
public class SupplyHouseSpringMysqlApplication implements CommandLineRunner{
	
	@Autowired
    private SupplierProductRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SupplyHouseSpringMysqlApplication.class, args);
	}
	
	@Override
    public void run(String... args) {
        System.out.println("\nfindAll()");
        repository.findAll().forEach(x -> System.out.println(x));
        
        
        TimerTask task = new TimerTask() {
            public void run() {
            	File folder = new File("C:\\Users\\Jeanne\\Documents\\workspace-spring-tool-suite-4-4.11.0.RELEASE\\SupplyHouse_SpringMysql\\data");
                SupplierProductService sp=new SupplierProductService(repository);
        		MyFileReader reader=new MyFileReader(sp);
        		reader.readDir(folder);
            }
        };
        
        //run every 30 sec
        Timer timer = new Timer("Adddata");
        long delay = 30000L;
        timer.schedule(task, 0, delay);
        
        /*ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime nextRun = now.withHour(11).withMinute(7).withSecond(0);
        
        // If 11:07:00 is in the past, add one day
        if(now.compareTo(nextRun) > 0)
            nextRun = nextRun.plusDays(1);

        // Get duration between now and 11:07:00
        Duration duration = Duration.between(now, nextRun);
        long initalDelay = duration.getSeconds();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);            
        scheduler.scheduleAtFixedRate(task,
            initalDelay,
            TimeUnit.DAYS.toSeconds(1),
            TimeUnit.SECONDS);*/
    }

}
