package ru.finnetrolle.scripting.harbinger.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.finnetrolle.scripting.harbinger.Harbinger;
import ru.finnetrolle.scripting.harbinger.util.Pair;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExampleRunner {

  static final String[] PATH_TO_DIR_WITH_SOURCES = {
    "processors"
        + File.separator
        + "src"
        + File.separator
        + "main"
        + File.separator
        + "groovy"
        + File.separator
        + "ru"
        + File.separator
        + "finnetrolle"
        + File.separator
        + "scripting"
        + File.separator
        + "harbinger"
        + File.separator
        + "processors"
        + File.separator
  };

  static final Map<String, String> SOURCE =
      Stream.of(
              Pair.from("1", "1000"),
              Pair.from("2", "season"),
              Pair.from("3", "world"),
              Pair.from("4", "123"),
              Pair.from("5", "322.48"))
          .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

  private static final Logger LOG = LoggerFactory.getLogger(ExampleRunner.class);

  static void logMap(Map<String, String> map) {
    map.forEach((k, v) -> LOG.info("{}={}", k, v));
  }

  public static void main(String[] args) {
    Harbinger harbinger = new Harbinger(PATH_TO_DIR_WITH_SOURCES);
    logMap(SOURCE);

    harbinger.loadProcessors("example.ini");
    logMap(harbinger.process(SOURCE));

    harbinger.loadProcessors("example2.ini");
    logMap(harbinger.process(SOURCE));
  }
}
