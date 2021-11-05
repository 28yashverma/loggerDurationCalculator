package loggerDuration.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import loggerDuration.model.Event;
import loggerDuration.model.EventDuration;
import loggerDuration.repository.EventDurationRepository;
import loggerDuration.repository.EventRepo;

@Transactional
@Service
public class ProcessLogger implements IProcessLoggerService {

	private static final Logger logger = Logger.getLogger(ProcessLogger.class);
	private static final String _started = "STARTED";
	private static final String _finished = "FINISHED";
	private static final String _path_To_File = "logfile.txt";

	@Autowired
	private EventRepo evtRepo;

	@Autowired
	private EventDurationRepository evtDurRepo;

	List<Event> listOfEvents = new ArrayList<Event>();
	Set<String> sEventsId = new HashSet<String>();

	public void initialize() {

		logger.info("Start to read file {logfile.txtx}");
		listOfEvents = readFile();

		logger.info("Save events into H2");
		saveEvents();

		for (Event e : evtRepo.findAll()) {
			sEventsId.add(e.getId());
		}

		for (String id : sEventsId)
			calculateEventDuration(id);

		System.out.println(evtDurRepo.findAll());
	}

	public List<Event> readFile() {
		Path pathToFile = Paths.get(_path_To_File);
		Gson gson = new Gson();
		Event event;

		List<Event> events = new ArrayList<Event>();
		Stream<String> lines = null;
		try {

			logger.info("Form stream of lines and events by subsequently reading file line by line..");
			lines = Files.lines(pathToFile);
			List<String> list = lines.collect(Collectors.toList());
			for (String string : list) {
				event = gson.fromJson(string, Event.class);
				events.add(event);
			}
		} catch (IOException e) {
			logger.error("Exception occured while reading the file with message " + e.getMessage());
			e.printStackTrace();
		} finally {
			logger.info("Close the stream of lines");
			lines.close();
		}

		lines.close();
		return events;
	}

	public void saveEvents() {
		if (listOfEvents.size() > 0)
			evtRepo.saveAll(listOfEvents);
		else
			return;
	}

	public void calculateEventDuration(String id) {
		Long duration = 0L;
		EventDuration evtDur = null;

		Stream<Event> strEvt = evtRepo.findById(id);
		List<Event> evts = strEvt.collect(Collectors.toList());

		logger.info("Get start of the event details");
		Event evtStart = evts.stream().filter(state -> state.getState().equalsIgnoreCase(_started)).findFirst().get();

		logger.info("Get finish of event details");
		Event evtFinish = evts.stream().filter(state -> state.getState().equalsIgnoreCase(_finished)).findFirst().get();

		logger.info("Calculate duration of event");
		if (evtFinish != null) {
			duration = evtFinish.getTimestamp() - evtStart.getTimestamp();
		}

		if (duration > 4) {
			evtDur = new EventDuration(evtStart.getId(), duration, evtStart.getType(), evtStart.getHost(), true);
		} else {
			evtDur = new EventDuration(evtStart.getId(), duration, evtStart.getType(), evtStart.getHost(), false);
		}

		if (evtDur != null)
			logger.info("save event");
		evtDurRepo.save(evtDur);

	}

}
