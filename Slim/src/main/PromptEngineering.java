package main;

import java.util.ArrayList;
import java.util.Scanner;

public class PromptEngineering {
	
	private static Scanner scanner = new Scanner(System.in);
	private static Scanner sc = new Scanner(System.in);
	
	private static void menu() {
		
		System.out.println("How can I help you? Please choose an option (enter option number) \n\n"
				           + "1 = Study plans \n"
				           + "2 = Coding concepts \n"
				           + "3 = Summarize tutorials \n"
				           + "4 = Explain code \n"
				           + "5 = Quizzes \n"
				           + "6 = Problem solving \n"
				           + "7 = Projects \n"
				           + "8 = Code reviews \n"
				           + "9 = Fix errors \n"
				           + "10 = Generate code \n"
				           + "11 = Write your own prompt \n");
		
		String userInput = scanner.nextLine();
		
		router1(userInput);
	}
	
	private static void router1(String userInput) {
		
		switch(userInput) {
		case "1": studyPlans(); break;
		case "2": codingConcepts(); break;
		case "3": summarizeTutorials(); break;
		case "4": explainCode(); break;
		case "5": quizzes(); break;
		case "6": problemSolving(); break;
		case "7": projects(); break;
		case "8": codeReviews(); break;
		case "9": fixErrors(); break;
		case "10": generateCode(); break;
		case "11": writePrompts(); break;
		default: 
			System.out.println("\nInvalid input, menu will be reshown. \n");
			menu();
		}
		
	}
	
	private static void quizzes() {
		  
		System.out.println("\nWhich technology or programming language do you prefer to use in the quiz? \n");
		String tech = scanner.nextLine();
		
		System.out.println("\nWhat is the topic you want to be assessed on? \n");
		String topic = scanner.nextLine();
		
		System.out.println("\nOn a scale of 1 to 10, what level of difficulty would you like the quiz questions to be? (enter a number) \n"
		           + "1 = extremely easy \n"
		           + "10 = extremely hard \n");
        String difficulty = scanner.nextLine();
		
        System.out.println("\nChoose the type of questions in the quiz: (enter option number) \n"
		           + "1 = multiple choice \n"
		           + "2 = true or false \n"
		           + "3 = code tracing \n"
		           + "4 = mix \n");
        String type = scanner.nextLine();
        
        System.out.println("\nChoose the number of questions of the quiz: (enter a number) \n");
		String noOfQuestions = scanner.nextLine();
        
		String prompt = "";
        switch(type) {
        case "1":
        	prompt = "Imagine that you are a programming tutor, give me programming questions, " + noOfQuestions + " multiple choice questions, the difficulty of the questions on a scale of 1 to 10 (1 is the easiest, 10 is the hardest) should be " + difficulty + ", the topic of the questions is " + topic + ", and you should use " + tech + " in any code provided or required in the questions, do not give me the answers of the questions unless I ask you to do that.";
        	break;
        case "2":
        	prompt = "Imagine that you are a programming tutor, give me programming questions, " + noOfQuestions + " true or false questions, the difficulty of the questions on a scale of 1 to 10 (1 is the easiest, 10 is the hardest) should be " + difficulty + ", the topic of the questions is " + topic + ", and you should use " + tech + " in any code provided or required in the questions, do not give me the answers of the questions unless I ask you to do that.";
        	break;
        case "3":
        	prompt = "Imagine that you are a programming tutor, give me programming questions, " + noOfQuestions + " code tracing questions, the difficulty of the questions on a scale of 1 to 10 (1 is the easiest, 10 is the hardest) should be " + difficulty + ", the topic of the questions is " + topic + ", and you should use " + tech + " in any code provided or required in the questions, do not give me the answers of the questions unless I ask you to do that.";
        	break;
        case "4":
        	prompt = "Imagine that you are a programming tutor, give me programming questions, " + noOfQuestions + " questions, the type of questions should be a mix between multiple choice, true or false, and code tracing questions, the difficulty of the questions on a scale of 1 to 10 (1 is the easiest, 10 is the hardest) should be " + difficulty + ", the topic of the questions is " + topic + ", and you should use " + tech + " in any code provided or required in the questions, do not give me the answers of the questions unless I ask you to do that.";
        	break;
        }
		
        String response = ChatGPT.callAPI(prompt);
        
        System.out.println("\n" + response + "\n");
        
        System.out.println("\nTo show the answers enter \"1\" : \n");
		String answers = scanner.nextLine();
		
		if(answers.equals("1")) {
			String prompt2 = "Give me all the right answers of the previous questions that you gave me, also explain why it is the right answer";
			
			ArrayList<String> contents = new ArrayList<String>();
		    contents.add(prompt);
		    contents.add(response);
		    contents.add(prompt2);
			
			String response2 = ChatGPT.callAPIArray(contents);
			contents.add(response2);
			System.out.println("\n" + response2 + "\n");
			
			followUp(contents);
			
		}
			
	}

	private static void summarizeTutorials() {
		
		System.out.println("\nDo you want to summarize an article or a YouTube video? (enter \"1\" or \"2\") \n"
				+ "1 = article \n"
				+ "2 = video \n");
		String type = scanner.nextLine();
		
		String link = "";
		String title = "";
		String channel = "";
		String prompt = "Imagine that you are a programming tutor";
		
		if(type.equals("1")) {
			System.out.println("\nPlease enter the article link: \n");
			link = scanner.nextLine();
			prompt += ", I want you to summarize this article for me, this is the article link: " + link;
		} else {
			System.out.println("\nPlease enter the YouTube video title: \n");
			title = scanner.nextLine();
			
			System.out.println("\nPlease enter the name of the channel that published the video: \n");
			channel = scanner.nextLine();
			
			prompt += ", I want you to summarize this YouTube video for me, this is its title: " + title + " by " + channel + " channel,";
		}
		
		System.out.println("\nDo you want the summary to focus on a specific aspect of the tutorial? (enter the aspect or \"skip\") \n");
        String focus = scanner.nextLine();
		
        if(focus.equals("skip"))
			prompt = prompt + " . finally, tell me if there are prerequisites to understand this tutorial.";
		else
			prompt = prompt + " . focus in your summary on " + focus + ", finally, tell me if there are prerequisites to understand this tutorial.";
		
        String response = ChatGPT.callAPI(prompt);
        System.out.println("\n" + response + "\n");
        
        ArrayList<String> contents = new ArrayList<String>();
	    contents.add(prompt);
	    contents.add(response);
	    
	    followUp(contents);
        
	}

	private static void writePrompts() {
		
		System.out.println("\nPlease write your prompt: (terminate it with \"$\") \n");
		sc.useDelimiter("\\$"); 
		String prompt = sc.next();
		
		String response = ChatGPT.callAPI(prompt);
        System.out.println("\n" + response + "\n");
	
        ArrayList<String> contents = new ArrayList<String>();
	    contents.add(prompt);
	    contents.add(response);
	    
	    followUp(contents);
        
	}

	private static void generateCode() {
		
		System.out.println("\nWhich programming language would you like the generated code to be written in? \n");
		String tech = scanner.nextLine();
		
		System.out.println("\nWhat is the functionality of the code you need? \n");
		String functionality = scanner.nextLine();
		
		System.out.println("\nPlease provide an input and its expected output: (or enter \"skip\") \n");
		String testCase = scanner.nextLine();
		
		String prompt = "";
		
		if(testCase.equals("skip"))
			prompt = "Imagine that you are a programming tutor, I want you to generate for me some code in " + tech + ", and I want you to explain it to me in details, the functionality of the code is: " + functionality + ", finally, tell me if there are prerequisites to understand this code.";
		else
			prompt = "Imagine that you are a programming tutor, I want you to generate for me some code in " + tech + ", and I want you to explain it to me in details, the functionality of the code is: " + functionality + ", and this is an input and its expected output: " + testCase + ", finally, tell me if there are prerequisites to understand this code.";
		
		String response = ChatGPT.callAPI(prompt);
        System.out.println("\n" + response + "\n");
		
        ArrayList<String> contents = new ArrayList<String>();
	    contents.add(prompt);
	    contents.add(response);
	    
	    followUp(contents);
        
	}

	private static void explainCode() {
		  
		System.out.println("\nPlease enter the code you want to be explained: (terminate the code with \"$\") \n");
		sc.useDelimiter("\\$"); 
		String code = sc.next();
		code = "``` " + code + " ```";
		
		System.out.println("\nWhat is the technology or programming language used in this code? \n");
		String tech = scanner.nextLine();
		
		System.out.println("\nWhat is your level of experience in " + tech + " on a scale of 1 to 10? (enter a number) \n"
		           + "1 = you know nothing \n"
		           + "10 = you know everything \n");
        String level = scanner.nextLine();
		
        System.out.println("\nWhat is the context in which this code was written? \n");
		String context = scanner.nextLine();
		
		System.out.println("\nDo you want the explanation to focus on specific lines of the code? (enter the line numbers or \"skip\") \n");
		String focus = scanner.nextLine();
        
		String prompt = "";
		
        if(focus.equals("skip"))
			prompt = "Imagine that you are a programming tutor, I will give you a piece of code in " + tech + ", and I want you to explain it to me in details: " + code + " the code is written in the following context: " + context + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", also tell me -if possible- the output or result of this code, finally, tell me if there are prerequisites to understand this code.";
		else
			prompt = "Imagine that you are a programming tutor, I will give you a piece of code in " + tech + ", and I want you to explain it to me in details: " + code + " focus in your explanation on the following lines of code: " + focus + " , the code is written in the following context: " + context + ", my knowledge level on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", also tell me -if possible- the output or result of this code, finally, tell me if there are prerequisites to understand this code.";
		
        String response = ChatGPT.callAPI(prompt);
        System.out.println("\n" + response + "\n");
        
        ArrayList<String> contents = new ArrayList<String>();
	    contents.add(prompt);
	    contents.add(response);
	    
	    followUp(contents);
        
	}

	private static void fixErrors() {

		System.out.println("\nPlease enter the code that has the error: (terminate the code with \"$\") \n");
		sc.useDelimiter("\\$"); 
		String code = sc.next();
		code = " ``` " + code + " ``` ";
		
		System.out.println("\nWhich technology or programming language is used in the code? \n");
		String tech = scanner.nextLine();
		
		System.out.println("\nWhat is the functionality of the code? \n");
		String functionality = scanner.nextLine();
		
		System.out.println("\nPlease enter the error message: (terminate the message with \"$\") \n");
		String message = sc.next();
		
		System.out.println("\nAre there any specific inputs or conditions that trigger the error? (enter the conditions or \"skip\") \n");
		String conditions = scanner.nextLine();
		
		String prompt = "";
		
		if(conditions.equals("skip"))
			prompt = "Imagine that you are a programming tutor, I will give you some " + tech + " code that I wrote, the functionality of the code is: " + functionality + ", I have an error in my code and I want you to help me fix it, explain to me the reason of the error and how to fix it in details. the code:" + code + "the error message: " + message + " .";
		else
			prompt = "Imagine that you are a programming tutor, I will give you some " + tech + " code that I wrote, the functionality of the code is: " + functionality + ", I have an error in my code and I want you to help me fix it, the error is triggered under the following coditions or input: " + conditions + ", explain to me the reason of the error and how to fix it in details. the code:" + code + "the error message: " + message + " .";
		
		String response = ChatGPT.callAPI(prompt);
        System.out.println("\n" + response + "\n");
		
        ArrayList<String> contents = new ArrayList<String>();
	    contents.add(prompt);
	    contents.add(response);
	    
	    followUp(contents);
        
	}

	private static void codeReviews() {
		
		System.out.println("\nPlease enter the code you want to be reviewed: (terminate the code with \"$\") \n");
		sc.useDelimiter("\\$"); 
		String code = sc.next();
		code = " ``` " + code + " ``` ";
		
		System.out.println("\nWhich technology or programming language is used in the code? \n");
		String tech = scanner.nextLine();
		
		System.out.println("\nWhat is the functionality of the code? \n");
		String functionality = scanner.nextLine();
		
		String prompt = "Imagine that you are a programming tutor, I will give you some " + tech + " code that I wrote, the functionality of the code is: " + functionality + ", I want you to review my code and give me your feedback in details, comment on correctness, efficiency and clean code, state time and space complexity of my code if possible, also tell me how can I improve my code if it needs to be improved, the code:" + code;
		
		String response = ChatGPT.callAPI(prompt);
        System.out.println("\n" + response + "\n");
        
        ArrayList<String> contents = new ArrayList<String>();
	    contents.add(prompt);
	    contents.add(response);
	    
	    followUp(contents);
        
	}

	private static void projects() {
		
		System.out.println("\nYou want to build a project! \n"
				         + "I can help you choose a project idea, \n"
				         + "or provide you with functional requirements for the idea you have in mind. (choose an option, enter \"1\" or \"2\")\n"
				         + "1 = project ideas \n"
				         + "2 = project requirements \n");
		String userInput = scanner.nextLine();
		
		if(userInput.equals("1"))
			projectIdeas();
		else
			projectRequirements();
		
	}

	private static void projectRequirements() {
			
		System.out.println("\nWhat is the main idea of your project? \n");
		String idea = scanner.nextLine();
		
		System.out.println("\nWhat is the type of the project? (ex: web app / mobile app ...) \n");
		String type = scanner.nextLine();
		
		System.out.println("\nWhat features are essential to you in the project? (enter the features or \"skip\") \n");
		String features = scanner.nextLine();
		
		System.out.println("\nWho are the users of the project or application? (ex: admin / client ...) \n"
				+ "(enter the users or \"skip\") \n");
		String users = scanner.nextLine();
		
		System.out.println("\nWhich technology or programming language you will use to build the project? \n");
		String tech = scanner.nextLine();
		
		System.out.println("\nWhat is your level of experience in " + tech + " on a scale of 1 to 10? (enter a number) \n"
		           + "1 = you know nothing \n"
		           + "10 = you know everything \n");
		String level = scanner.nextLine();
		
		String prompt = "";
		
		if(features.equals("skip")) {
			if(users.equals("skip"))
				prompt = "Imagine that you are a programming tutor, I want to build a coding project, " + idea + ", the type of the project is a " + type + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", give me the functional requirements of this project, the requirements should be detailed and realistic, this is an example of a requirement format: As [a user persona], I want [to perform this action] so that [I can accomplish this goal], the requirements should be ordered in the order of implementation.";
			else
				prompt = "Imagine that you are a programming tutor, I want to build a coding project, " + idea + ", the type of the project is a " + type + ", the users of the project are: " + users + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", give me the functional requirements of this project, the requirements should be detailed and realistic, this is an example of a requirement format: As [a user persona], I want [to perform this action] so that [I can accomplish this goal], the requirements should be ordered in the order of implementation.";
		} else {
			if(users.equals("skip"))
				prompt = "Imagine that you are a programming tutor, I want to build a coding project, " + idea + ", the type of the project is a " + type + ", some features of the project are: " + features + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", give me the functional requirements of this project, the requirements should be detailed and realistic, this is an example of a requirement format: As [a user persona], I want [to perform this action] so that [I can accomplish this goal], the requirements should be ordered in the order of implementation.";
			else
				prompt = "Imagine that you are a programming tutor, I want to build a coding project, " + idea + ", the type of the project is a " + type + ", the users of the project are: " + users + ", some features of the project are: " + features + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", give me the functional requirements of this project, the requirements should be detailed and realistic, this is an example of a requirement format: As [a user persona], I want [to perform this action] so that [I can accomplish this goal], the requirements should be ordered in the order of implementation.";
		}	
		
		String response = ChatGPT.callAPI(prompt);
        System.out.println("\n" + response + "\n");
        
        ArrayList<String> contents = new ArrayList<String>();
	    contents.add(prompt);
	    contents.add(response);
	    
	    followUp(contents);
		
	}

	private static void projectIdeas() {
		
		System.out.println("\nWhat is the type of project you have in mind? (ex: web app / mobile app ...) \n");
		String type = scanner.nextLine();
		
		System.out.println("\nWhich technology or programming language you want to use to build the project? \n");
		String tech = scanner.nextLine();
		
		System.out.println("\nWhat is your level of experience in " + tech + " on a scale of 1 to 10? (enter a number) \n"
		           + "1 = you know nothing \n"
		           + "10 = you know everything \n");
		String level = scanner.nextLine();
		
		System.out.println("\nDo you have any preferences for the domain or industry your project relates to? (ex: finance, healthcare, education) \n"
				   + "(enter the domain or \"skip\")");
		String domain = scanner.nextLine();
		
		String prompt = "";
		
		if(domain.equals("skip"))
			prompt = "Imagine that you are a programming tutor, give me 20 ideas for coding projects, specifically, the type of the project should be " + type + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ".";
		else
			prompt = "Imagine that you are a programming tutor, give me 20 ideas for coding projects, specifically, the type of the project should be " + type + ", and the project should be related to " + domain + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ".";
		
		String response = ChatGPT.callAPI(prompt);
        System.out.println("\n" + response + "\n");
		
        ArrayList<String> contents = new ArrayList<String>();
	    contents.add(prompt);
	    contents.add(response);
	    
	    followUp(contents);
        
	}

	private static void problemSolving() {
		
		System.out.println("\nWhich programming language do you prefer to use? \n");
		String language = scanner.nextLine();
		
		System.out.println("\nWhat specific topic would you like the problem to focus on? \n");
		String topic = scanner.nextLine();
		
		System.out.println("\nOn a scale of 1 to 10, what level of difficulty would you like the problem to be? (enter a number) \n"
		           + "1 = extremely easy \n"
		           + "10 = extremely hard \n");
        String difficulty = scanner.nextLine();
		
        String prompt = "Imagine that you are a programming tutor, give me a programming problem so that I will attempt to solve, I plan to solve it using " + language + ", the problem should focus on " + topic + ", the difficulty of the problem on a scale of 1 to 10 (1 = extremely easy, 10 = extremely hard) should be " + difficulty + ", the problem statement should be clear and detailed, also you should provide me with examples of different inputs and their expected outputs.";
        
        String response = ChatGPT.callAPI(prompt);
        System.out.println("\n" + response + "\n");
        
        System.out.println("\nEnter your solution to be reviewed: (terminate the code with \"$\") \n");
        sc.useDelimiter("\\$"); 
        String code = sc.next();
		code = " ``` " + code + " ``` ";
		
		String prompt2 = "I will provide you with my solution to the problem, explain whether it is correct or not in details, also comment on the efficiency of my code, If my solution is not correct give me hints to help me costruct a correct solution, this is my solution: " + code;
		
		ArrayList<String> contents = new ArrayList<String>();
	    contents.add(prompt);
	    contents.add(response);
	    contents.add(prompt2);
		
		String response2 = ChatGPT.callAPIArray(contents);
		contents.add(response2);
		System.out.println("\n" + response2 + "\n");
		
		followUp(contents);
		
	}

	private static void codingConcepts() {
		
		System.out.println("\nWhat is the programming concept you want me to explain? \n");
		String concept = scanner.nextLine();
		
		System.out.println("\nDo you want to learn " + concept + " for a specific usage? (enter the usage or \"skip\") \n");
		String usage = scanner.nextLine();
		
		System.out.println("\nWhich technology or programming language do you prefer to use to explain " + concept + "? \n");
		String tech = scanner.nextLine();
		
		System.out.println("\nWhat is your level of experience in " + tech + " on a scale of 1 to 10? (enter a number) \n"
		           + "1 = you know nothing \n"
		           + "10 = you know everything \n");
        String level = scanner.nextLine();
		
        String prompt = "";
        
        if(usage.equals("skip"))
			prompt = "Imagine that you are a programming tutor, explain to me " + concept + " in details, use " + tech + " in your explanation, my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", include practical coding examples in your explanation, also tell me if there are prerequisites to understand " + concept + " well, finally, provide me with links to resources explaining this topic for more information.";
		else
			prompt = "Imagine that you are a programming tutor, explain to me " + concept + " in details, I want to learn " + concept + " to use it in " + usage + ", use " + tech + " in your explanation, my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", include practical coding examples in your explanation, also tell me if there are prerequisites to understand " + concept + " well, finally, provide me with links to resources explaining this topic for more information.";
        
        String response = ChatGPT.callAPI(prompt);
        System.out.println("\n" + response + "\n");
        
        ArrayList<String> contents = new ArrayList<String>();
        contents.add(prompt);
        contents.add(response);
        
        followUp(contents);
        
	}

	private static void studyPlans() {
		
		System.out.println("\nWhich technology or programming language do you plan to study? \n");
		String tech = scanner.nextLine();
		
		System.out.println("\nWhat is your level of experience in " + tech + " on a scale of 1 to 10? (enter a number) \n"
				           + "1 = you know nothing \n"
				           + "10 = you know everything \n");
		String level = scanner.nextLine();
		
		System.out.println("\nDo you prefer learning by reading or watching? (enter \"1\" or \"2\") \n"
				+ "1 = reading \n"
				+ "2 = watching \n");
		String learningType = scanner.nextLine();
		
		System.out.println("\nDo you have a problem with paid resources? (enter \"1\" or \"2\") \n"
				+ "1 = yes \n"
				+ "2 = no \n");
		String paidResources = scanner.nextLine();
		
		System.out.println("\nHow many hours per week can you dedicate for studying? (enter a number) \n");
		String hoursPerWeek = scanner.nextLine();
		
		String prompt = "";
		
		if(learningType.equals("1")) {
			if(paidResources.equals("1"))
				prompt = "I want to learn " + tech + ", my knowledge level on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", imagine that you are a programming tutor, give me a detailed study plan to master " + tech + ", I can dedicate " + hoursPerWeek + " hours per week for studying, I prefer studying by reading, provide me with links to all free resources needed.";
			else
				prompt = "I want to learn " + tech + ", my knowledge level on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", imagine that you are a programming tutor, give me a detailed study plan to master " + tech + ", I can dedicate " + hoursPerWeek + " hours per week for studying, I prefer studying by reading, provide me with links to all resources needed.";
		} else {
			if(paidResources.equals("1"))
				prompt = "I want to learn " + tech + ", my knowledge level on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", imagine that you are a programming tutor, give me a detailed study plan to master " + tech + ", I can dedicate " + hoursPerWeek + " hours per week for studying, I prefer studying by watching, provide me with links to all free resources needed.";
			else
				prompt = "I want to learn " + tech + ", my knowledge level on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", imagine that you are a programming tutor, give me a detailed study plan to master " + tech + ", I can dedicate " + hoursPerWeek + " hours per week for studying, I prefer studying by watching, provide me with links to all resources needed.";	
		}
		
		String response = ChatGPT.callAPI(prompt);
        System.out.println("\n" + response + "\n");
        
        ArrayList<String> contents = new ArrayList<String>();
	    contents.add(prompt);
	    contents.add(response);
	    
	    followUp(contents);
		
	}

	private static void followUp(ArrayList<String> arr) {
		
		 while(true) {
			 System.out.println("\nwrite your follow up prompt: (or enter \"end chat\" to end the chat) \n"
			 		+ "(terminate your input with \"$\")");
		     sc.useDelimiter("\\$"); 
		     String prompt = sc.next();
		     
		     if(prompt.contains("end chat"))
		    	 break;
		     
		     arr.add(prompt);
		     
		     String response = ChatGPT.callAPIArray(arr);
		     arr.add(response);
		     System.out.println("\n" + response + "\n");
		 }
		 
	}
	
	public static void start() {
		
		System.out.println("Welcome to Slim! Your AI assistant for learning programming. \n");
		
		menu();
		
	}
	
	public static void main(String[] args) {
		
		start();
		
		scanner.close();
		sc.close();
		
	}

}