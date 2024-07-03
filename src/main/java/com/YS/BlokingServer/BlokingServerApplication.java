package com.YS.BlokingServer;

import com.YS.BlokingServer.Repository.PaymentRepository;
import com.YS.BlokingServer.Repository.StudentRepository;
import com.YS.BlokingServer.entities.Payment;
import com.YS.BlokingServer.entities.Student;
import com.YS.BlokingServer.enume.PaymentStatus;
import com.YS.BlokingServer.enume.PaymentType;
import com.YS.BlokingServer.service.Student.StudentService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Blogging",
				version = "1.0.0",
				description = "This project is only for blogging",
				contact = @Contact(
						name = "Mr Yassir",
						email = "yassir.silit@outlook.com"
				)
		)
)
public class BlokingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlokingServerApplication.class, args);
	}

	//@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
										PaymentRepository paymentRepository){
		return args -> {
			Student student = new Student(UUID.randomUUID().toString(),"Samir","Samir","SMI03","InfoS1","https://images.pexels.com/photos/26125407/pexels-photo-26125407/free-photo-of-ete-animal-feuille-exterieur.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load");
			studentRepository.save(student);
			studentRepository.save(Student.builder()
							.id(UUID.randomUUID().toString())
							.firstName("Halima")
							.lastName("Halima")
							.code("SMI04")
							.programId("InfoS1")
							.photo("https://images.pexels.com/photos/26125407/pexels-photo-26125407/free-photo-of-ete-animal-feuille-exterieur.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load")
							.build());
			studentRepository.findAll().forEach(st->{

				PaymentType[] paymentTypes = PaymentType.values();
				Random random = new Random();

				for (int i = 0; i <2 ; i++) {
					int index = random.nextInt(paymentTypes.length);
					Payment payment = Payment.builder()
							.student(st)
							.type(paymentTypes[index])
							.date(LocalDate.now())
							.amount(1000+(int)(Math.random()*20000))
							.status(PaymentStatus.CREATED)
							.build();
					paymentRepository.save(payment);
				}
			});
		};
	}
}
