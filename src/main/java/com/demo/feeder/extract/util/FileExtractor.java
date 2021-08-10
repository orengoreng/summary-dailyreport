package com.demo.feeder.extract.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * File extractor that reads files each line and put into an arraylist
 *
 */
@Slf4j
public class FileExtractor {

	public static List<String> extractAllFiles(String inputFolder) throws IOException {
		List<String> lines = null;
		try {
			if (Files.walk(Paths.get(inputFolder)) != null) {
				lines = Files.walk(Paths.get(inputFolder)).filter(Files::isRegularFile)
						.map(t -> {
							log.info("Reading file: {} ", t.getFileName());
							List<String> result = null;
							try {
								result = Files.readAllLines(t);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return result;
						}).flatMap(List::stream).collect(Collectors.toList());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return lines;
	}

	public static void moveFiles(String inputFolder, String moveFolder)
			throws IOException {
		FileUtils.copyDirectory(new File(inputFolder), new File(moveFolder));
		for (File f : new File(inputFolder).listFiles())
			f.delete();
	}

}
