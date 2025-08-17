package ru.zaikin.kafka_sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.zaikin.kafka_sandbox.kafka.SandboxProducer;

import java.util.Scanner;

@SpringBootApplication
public class KafkaSandboxApplication implements CommandLineRunner {


	@Autowired
	private SandboxProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaSandboxApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter messages to send to Kafka (type 'exit' to quit):");

		int count = 1;
		while (true) {
			System.out.print("> ");
			String input = scanner.nextLine();
			if ("exit".equalsIgnoreCase(input)) {
				break;
			}
			producer.sendEvent("event-" + count++, input);
		}

		System.out.println("Exiting...");
		scanner.close();
	}
}
