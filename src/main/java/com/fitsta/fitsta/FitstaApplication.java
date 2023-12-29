package com.fitsta.fitsta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fitsta.fitsta.Entity.Orders;
import com.fitsta.fitsta.Entity.Plans;
import com.fitsta.fitsta.Entity.PlansPurchase;
import com.fitsta.fitsta.Entity.Product;
import com.fitsta.fitsta.Entity.Task;
import com.fitsta.fitsta.Entity.Trainer;
import com.fitsta.fitsta.Entity.User;
import com.fitsta.fitsta.Repository.ProductRepository;
import com.fitsta.fitsta.Repository.TrainerRepository;

@SpringBootApplication
public class FitstaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FitstaApplication.class, args);
		// ProductRepository productRepository = context.getBean(ProductRepository.class);
		TrainerRepository trainerRepository = context.getBean(TrainerRepository.class);
		ProductRepository productRepository = context.getBean(ProductRepository.class);

		// SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		// try {

		// 	Trainer t1 = new Trainer();
		// 	t1.setId(0);
		// 	t1.setName("Harsh");
		// 	t1.setDob(new java.sql.Date(dateFormat.parse("11/10/93").getTime()));
		// 	t1.setGender("Male");
		// 	t1.setContactno("1234567890");
		// 	t1.setImage("/img/t1.png");
		// 	t1.setSpecialization("Weight Loss");
		// 	t1.setExperience("5 Yrs");
		// 	t1.setUsername("harsh@gmail.com");
		// 	t1.setPassword("Pass@123");

		// 	PlansPurchase pp1 = new PlansPurchase();
		// 	pp1.setId(0);
		// 	pp1.setPurchasedate(new java.sql.Date(dateFormat.parse("26/08/23").getTime()));
		// 	pp1.setExpirydate(new java.sql.Date(dateFormat.parse("26/09/23").getTime()));
		// 	PlansPurchase pp2 = new PlansPurchase();
		// 	pp2.setId(0);
		// 	pp2.setPurchasedate(new java.sql.Date(dateFormat.parse("25/08/23").getTime()));
		// 	pp2.setExpirydate(new java.sql.Date(dateFormat.parse("25/09/23").getTime()));

		// 	List<User> users = new ArrayList<>();
		// 	User u1 = new User();
		// 	// u1.setId(0);
		// 	u1.setName("Om");
		// 	u1.setDob(new java.sql.Date(dateFormat.parse("04/11/03").getTime()));
		// 	u1.setGender("Male");
		// 	u1.setContactno("7410852963");
		// 	u1.setAddress("Begampur");
		// 	u1.setWeight(50);
		// 	u1.setHeight(164);
		// 	u1.setUsername("om04@gmail.com");
		// 	u1.setPassword("Pass@123");
		// 	u1.setTrainer(t1);
		// 	u1.setUserPlansPurchase(pp1);
		// 	users.add(u1);

		// 	User u2 = new User();
		// 	// u2.setId(0);
		// 	u2.setName("Imran");
		// 	u2.setDob(new java.sql.Date(dateFormat.parse("10/10/03").getTime()));
		// 	u2.setGender("Male");
		// 	u2.setContactno("8529637410");
		// 	u2.setAddress("Bramhapuri");
		// 	u2.setWeight(60);
		// 	u2.setHeight(162);
		// 	u2.setUsername("imran@gmail.com");
		// 	u2.setPassword("Pass@123");
		// 	u2.setTrainer(t1);
		// 	u2.setUserPlansPurchase(pp2);
		// 	users.add(u2);
			
		// 	Task tsk1 = new Task(0, new java.sql.Date(dateFormat.parse("26/09/23").getTime()), "Cardio", "80g Paneer", "2", "1500", true, t1, u1);
		// 	Task tsk2 = new Task(0, new java.sql.Date(dateFormat.parse("27/09/23").getTime()), "Leg", "80g Paneer", "2", "1000", false, t1, u1);
		// 	Task tsk3 = new Task(0, new java.sql.Date(dateFormat.parse("26/09/23").getTime()), "Cardio", "80g Paneer", "2", "1200", true, t1, u2);
		// 	Task tsk4 = new Task(0, new java.sql.Date(dateFormat.parse("27/09/23").getTime()), "Leg", "80g Paneer", "2", "1300", false, t1, u2);
		// 	List<Task> tasks = new ArrayList<>();
		// 	tasks.add(tsk1);
		// 	tasks.add(tsk2);
		// 	tasks.add(tsk3);
		// 	tasks.add(tsk4);

		// 	List<Task> u1Tasks = new ArrayList<>();
		// 	u1Tasks.add(tsk1);
		// 	u1Tasks.add(tsk2);
		// 	u1.setTasks(u1Tasks);

		// 	List<Task> u2Tasks = new ArrayList<>();
		// 	u2Tasks.add(tsk3);
		// 	u2Tasks.add(tsk4);
		// 	u2.setTasks(u2Tasks);

		// 	t1.setTasks(tasks);

		// 	pp1.setEnrolleduser(u1);
		// 	pp2.setEnrolleduser(u2);
		// 	// t1.setUsers(users);

		// 	List<PlansPurchase> plansPurchases = new ArrayList<>();
		// 	plansPurchases.add(pp1);
		// 	plansPurchases.add(pp2);

		// 	List<Plans> plans = new ArrayList<>();
		// 	Plans p1 = new Plans(0, "Gravy Losser", "Weight Loss", "Money Back Gaureenty\nPremium Service\nDaily Diet Controll Remainder", 2599, 1, t1, plansPurchases);
		// 	plans.add(p1);
		// 	t1.setPlans(plans);

		// 	pp1.setEnrolledplan(p1);
		// 	pp2.setEnrolledplan(p1);

		// 	Product pro1 = new Product();
		// 	pro1.setName("MB Weight Gainer");
		// 	pro1.setImage1("/product/img1.jpg");
		// 	pro1.setImage2("/product/img2.jpg");
		// 	pro1.setImage3("/product/img3.jpg");
		// 	pro1.setImage4("/product/img4.jpg");
		// 	pro1.setDescription("Weight Gainer from Muscle Blaze containing carbos and other ingredients.");
		// 	pro1.setProductPrice("5000");
		// 	Product savedProduct = productRepository.save(pro1);

		// 	List<Orders> u1Orders = new ArrayList<>();
		// 	Orders o1 = new Orders();
		// 	o1.setId(0);
		// 	o1.setOrderDate(new java.sql.Date(dateFormat.parse("28/09/23").getTime()));
		// 	o1.setTotalAmount(5000);
		// 	o1.setOrderUser(u1);
		// 	o1.setOrderProduct(savedProduct); 
		// 	u1Orders.add(o1);
		// 	u1.setOrders(u1Orders);
			
		// 	List<Orders> u2Orders = new ArrayList<>();
		// 	Orders o2 = new Orders();
		// 	o2.setId(0);
		// 	o2.setOrderDate(new java.sql.Date(dateFormat.parse("28/09/23").getTime()));
		// 	o2.setTotalAmount(5000);
		// 	o2.setOrderUser(u2);
		// 	o2.setOrderProduct(savedProduct); 
		// 	u2Orders.add(o2);
		// 	u2.setOrders(u2Orders);
			
		// 	t1.setUsers(users);


		// 	Trainer savedTrainer = trainerRepository.save(t1);


		// 	System.out.println("Trainer -> Each user :");
		// 	List<User> trainerUsers = savedTrainer.getUsers();
		// 	for (User eachUser : trainerUsers) {
		// 		System.out.println("\t Name : "+eachUser.getName());
		// 		System.out.println("\t Users Trainer : "+eachUser.getTrainer().getName());
		// 		System.out.println("\t Users -> Purchsed Plan -> Name : "+eachUser.getUserPlansPurchase().getEnrolledplan().getName());
		// 		System.out.println("\t Users -> Purchsed Plan -> Purchased Date : "+eachUser.getUserPlansPurchase().getPurchasedate());
		// 		System.out.println("\t Users -> Purchsed Plan -> Expiry Date : "+eachUser.getUserPlansPurchase().getExpirydate());
		// 		for (Task eachTask : eachUser.getTasks()) {
		// 			System.out.println("\t Users -> Task -> ID : "+ eachTask.getId());
		// 			System.out.println("\t Users -> Task -> Date : "+ eachTask.getWorkoutDate());
		// 			System.out.println("\t Users -> Task -> Workout : "+ eachTask.getWorkout());
		// 			System.out.println("\t Users -> Task -> Completed? : "+ eachTask.getIscompleted());
		// 		}

		// 		for (Orders eachOrders : eachUser.getOrders()) {
		// 			System.out.println("\t Users -> Orders -> Id : "+ eachOrders.getId());
		// 			System.out.println("\t Users -> Orders -> Product -> Name : "+ eachOrders.getOrderProduct().getName());
		// 			System.out.println("\t Users -> Orders -> User -> Name : "+ eachOrders.getOrderUser().getName());
		// 			System.out.println("\t Users -> Orders -> Total Amount : "+ eachOrders.getTotalAmount());
		// 			System.out.println("\t Users -> Orders -> Date : "+ eachOrders.getOrderDate());
		// 		}
		// 	}

		// 	System.out.println("\nTrainers -> Plans : ");
		// 	List<Plans> trainerPlans = savedTrainer.getPlans();
		// 	for (Plans eachPlan : trainerPlans) {
		// 		System.out.println("\t Plan Name : "+eachPlan.getName());
		// 		System.out.println("\t Trainer of Plan : "+eachPlan.getPlanstrainer().getName());
		// 	}

		// 	System.out.println("\nTrainer -> Tasks :");
		// 	for (Task eachTask : savedTrainer.getTasks()) {
		// 		System.out.println("\t Task Date : "+ eachTask.getWorkoutDate());
		// 		System.out.println("\t Task Workout : "+ eachTask.getWorkout());
		// 		System.out.println("\t Task Status : "+ eachTask.getIscompleted());
		// 	}


		// } catch (ParseException e) {e.printStackTrace();}


	}

}