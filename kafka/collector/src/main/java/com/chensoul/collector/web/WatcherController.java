package com.chensoul.collector.web;

import com.chensoul.collector.entity.AccurateWatcherMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatcherController {

	@RequestMapping(value = "/accurateWatch")
	public String watch(@RequestBody AccurateWatcherMessage accurateWatcherMessage) {
		System.err.println("----告警内容----:" + accurateWatcherMessage);
		return "is watched" + accurateWatcherMessage;
	}
}
