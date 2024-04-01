package com.patrick.elasticsearchsample.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class LogGenerator {

    private static final Logger log = LoggerFactory.getLogger(LogGenerator.class);

    @Scheduled(cron = "0/10 * * * * *")
    void normalLogGen() {
        log.info("With a sigh of relief, she finally reached the summit of the mountain, greeted by a breathtaking view that made the arduous journey worth every step");
        log.info("In the quiet solitude of the forest, the rustling leaves and chirping birds provided a soothing melody that calmed her restless mind");
        log.info("Lost in thought, he wandered the bustling streets of the city, searching for answers amidst the cacophony of voices and honking cars.");
        log.info("Under the soft glow of the moonlight, they danced together in the moonlit clearing, their laughter echoing through the night as they twirled and spun in each other's arms.");
    }
}
