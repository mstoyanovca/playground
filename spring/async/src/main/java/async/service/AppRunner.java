package async.service;

import async.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class);
    private final GitHubLookupService gitHubLookupService;

    @Autowired
    public AppRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();

        CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");
        CompletableFuture.allOf(page1, page2, page3).join();

        LOGGER.info("Elapsed time: {}", System.currentTimeMillis() - start);
        LOGGER.info("--> {}", page1.get());
        LOGGER.info("--> {}", page2.get());
        LOGGER.info("--> {}", page3.get());
    }
}
