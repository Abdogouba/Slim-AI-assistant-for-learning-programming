package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PromptEngineering {
	
	private GUI gui;
	private ArrayList<String> chat;
	private ArrayList<String> userInputs;
	private Boolean isAppJustRan;
	private String state;
	
	public PromptEngineering(GUI gui) {
		
		this.gui = gui;

		chat = new ArrayList<String>();
		
		userInputs = new ArrayList<String>();
		
		state = "start chat";
		
		addNewChatButtonActionListener();
		
		addSendButtonActionListener();
		
		isAppJustRan = true;
		
		startChat();
		
	}
	
	private void addSendButtonActionListener() {

		ActionListener sendButtonActionListener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if(!gui.getPrompt().getText().isBlank()) {
			    	String prompt = displayUserMessage();
			    	
			    	switch(state) {
			    	case "start chat": router(prompt); break;
			    	case "write prompts": actionPerformedWritePrompts(prompt); break;
			    	case "study plans tech": studyPlansTech(prompt); break;
			    	case "study plans level": studyPlansLevel(prompt); break;  
			    	case "study plans learning type": studyPlansLearningType(prompt); break; 
			    	case "study plans paid resources": studyPlansPaidResources(prompt); break;
			    	case "study plans no of hours": studyPlansNoOfHours(prompt); break;
			    	case "coding concepts concept": codingConceptsConcept(prompt); break;
			    	case "coding concepts usage": codingConceptsUsage(prompt); break;
			    	case "coding concepts tech": codingConceptsTech(prompt); break;
			    	case "coding concepts level": codingConceptsLevel(prompt); break;
//			    	case "summarize tutorials type": summarizeTutorialsType(prompt); break;
//			    	case "summarize tutorials video title": summarizeTutorialsVideoTitle(prompt); break;
//			    	case "summarize tutorials article link or video channel": summarizeTutorialsArticleLinkOrVideoChannel(prompt); break;
//			    	case "summarize tutorials aspect": summarizeTutorialsAspect(prompt); break;
			    	case "explain code code": explainCodeCode(prompt); break;
			    	case "explain code tech": explainCodeTech(prompt); break;
			    	case "explain code level": explainCodeLevel(prompt); break;
			    	case "explain code context": explainCodeContext(prompt); break;
			    	case "explain code lines": explainCodeLines(prompt); break;
			    	case "quizzes tech": quizzesTech(prompt); break;
			    	case "quizzes topic": quizzesTopic(prompt); break;
			    	case "quizzes difficulty": quizzesDifficulty(prompt); break;
			    	case "quizzes type": quizzesType(prompt); break;
			    	case "quizzes no of questions": quizzesNoOfQuestions(prompt); break;
			    	case "quizzes solution": quizzesSolution(prompt); break;
			    	case "problem solving tech": problemSolvingTech(prompt); break;
			    	case "problem solving topic": problemSolvingTopic(prompt); break;
			    	case "problem solving difficulty": problemSolvingDifficulty(prompt); break;
			    	case "problem solving solution": problemSolvingSolution(prompt); break;
			    	case "projects ideas or requirements": projectsIdeasOrRequirements(prompt); break;
			    	case "projects ideas type": projectsIdeasType(prompt); break;
			    	case "projects ideas tech": projectsIdeasTech(prompt); break;
			    	case "projects ideas level": projectsIdeasLevel(prompt); break;
			    	case "projects ideas domain": projectsIdeasDomain(prompt); break;
			    	case "projects requirements idea": projectsRequirementsIdea(prompt); break;
			    	case "projects requirements type": projectsRequirementsType(prompt); break;
			    	case "projects requirements features": projectsRequirementsFeatures(prompt); break;
			    	case "projects requirements users": projectsRequirementsUsers(prompt); break;
			    	case "projects requirements tech": projectsRequirementsTech(prompt); break;
			    	case "projects requirements level": projectsRequirementsLevel(prompt); break;
			    	case "generate code tech": generateCodeTech(prompt); break;
			    	case "generate code functionality": generateCodeFunctionality(prompt); break;
			    	case "generate code test case": generateCodeTestCase(prompt); break;
			    	case "code reviews code": codeReviewsCode(prompt); break;
			    	case "code reviews tech": codeReviewsTech(prompt); break;
			    	case "code reviews functionality": codeReviewsFunctionality(prompt); break;
			    	case "fix errors code": fixErrorsCode(prompt); break;
			    	case "fix errors tech": fixErrorsTech(prompt); break;
			    	case "fix errors functionality": fixErrorsFunctionality(prompt); break;
			    	case "fix errors message": fixErrorsMessage(prompt); break;
			    	case "fix errors conditions": fixErrorsConditions(prompt); break;
			    	}
		    	}
		    }
		};
		
		this.gui.getSend().addActionListener(sendButtonActionListener);
		
	}
	
	protected void fixErrorsConditions(String prompt) {
		
		gui.getSend().setEnabled(false);
		
		userInputs.add(prompt);
		
		String code = userInputs.get(0);
		String tech = userInputs.get(1);
		String functionality = userInputs.get(2);
		String message = userInputs.get(3);
		String conditions = userInputs.get(4);

		if(conditions.equals("skip"))
			prompt = "Imagine that you are a programming tutor, I will give you some " + tech + " code that I wrote, the functionality of the code is: " + functionality + ", I have an error in my code and I want you to help me fix it, explain to me the reason of the error and how to fix it in details. the code:" + code + "the error message: " + message + " .";
		else
			prompt = "Imagine that you are a programming tutor, I will give you some " + tech + " code that I wrote, the functionality of the code is: " + functionality + ", I have an error in my code and I want you to help me fix it, the error is triggered under the following conditions or input: " + conditions + ", explain to me the reason of the error and how to fix it in details. the code: ``` " + code + " ``` . The error message: " + message + " .";
		
		chat.add(prompt);
		
		String response = ChatGPT.callAPI(chat);
		chat.add(response);
		displaySlimResponse(response);
		
		String text = "Now, you can write me your follow up messages. \nYou have my full attention!";
		displaySlimResponse(text);
		
		this.state = "write prompts";
		
	    gui.getSend().setEnabled(true);
		
	}

	protected void fixErrorsMessage(String prompt) {
		
		userInputs.add(prompt);
		String text = "Are there any specific inputs or conditions that trigger the error? \n(provide me with the conditions or \"skip\")";
		displaySlimResponse(text);
		this.state = "fix errors conditions";
		
	}

	protected void fixErrorsFunctionality(String prompt) {
		
		userInputs.add(prompt);
		String text = "Please provide me with the error message:";
		displaySlimResponse(text);
		this.state = "fix errors message";
		
	}

	protected void fixErrorsTech(String prompt) {
		
		userInputs.add(prompt);
		String text = "What is the functionality of the code?";
		displaySlimResponse(text);
		this.state = "fix errors functionality";
		
	}

	protected void fixErrorsCode(String prompt) {
		
		userInputs.add(prompt);
		String text = "Which technology or programming language is used in the code?";
		displaySlimResponse(text);
		this.state = "fix errors tech";
		
	}

	protected void codeReviewsFunctionality(String prompt) {
		
		gui.getSend().setEnabled(false);
		
		userInputs.add(prompt);
		
		String code = userInputs.get(0);
		String tech = userInputs.get(1);
		String functionality = userInputs.get(2);

		prompt = "Imagine that you are a programming tutor, I will give you some " + tech + " code that I wrote, the functionality of the code is: " + functionality + ", I want you to review my code and give me your feedback in details, comment on correctness, efficiency and clean code, state time and space complexity of my code if possible, also tell me how can I improve my code if it needs to be improved, finally, rate my code out of 10, the code: ``` " + code + " ``` .";
		
		chat.add(prompt);
		
		String response = ChatGPT.callAPI(chat);
		chat.add(response);
		displaySlimResponse(response);
		
		String text = "Now, you can write me your follow up messages. \nYou have my full attention!";
		displaySlimResponse(text);
		
		this.state = "write prompts";
		
	    gui.getSend().setEnabled(true);
		
	}

	protected void codeReviewsTech(String prompt) {
		
		userInputs.add(prompt);
		String text = "What is the functionality of the code?";
		displaySlimResponse(text);
		this.state = "code reviews functionality";
		
	}

	protected void codeReviewsCode(String prompt) {
		
		userInputs.add(prompt);
		String text = "Which technology or programming language is used in the code?";
		displaySlimResponse(text);
		this.state = "code reviews tech";
		
	}

	protected void generateCodeTestCase(String prompt) {
		
		gui.getSend().setEnabled(false);
		
		userInputs.add(prompt);
		
		String tech = userInputs.get(0);
		String functionality = userInputs.get(1);
		String testCase = userInputs.get(2);

		if(testCase.equals("skip"))
			prompt = "Imagine that you are a programming tutor, I want you to generate for me some code in " + tech + ", and I want you to explain it to me in details, the functionality of the code required is: " + functionality + ", finally, tell me if there are prerequisites to understand this code.";
		else
			prompt = "Imagine that you are a programming tutor, I want you to generate for me some code in " + tech + ", and I want you to explain it to me in details, the functionality of the code required is: " + functionality + ", and this is an input and its expected output: " + testCase + ", finally, tell me if there are prerequisites to understand this code.";
		
		chat.add(prompt);
		
		String response = ChatGPT.callAPI(chat);
		chat.add(response);
		displaySlimResponse(response);
		
		String text = "Now, you can write me your follow up messages. \nYou have my full attention!";
		displaySlimResponse(text);
		
		this.state = "write prompts";
		
	    gui.getSend().setEnabled(true);
		
	}

	protected void generateCodeFunctionality(String prompt) {
		
		userInputs.add(prompt);
		String text = "Please provide an input and its expected output: (or enter \"skip\")";
		displaySlimResponse(text);
		this.state = "generate code test case";
		
	}

	protected void generateCodeTech(String prompt) {
		
		userInputs.add(prompt);
		String text = "What is the functionality of the code you need?";
		displaySlimResponse(text);
		this.state = "generate code functionality";
		
	}

	protected void projectsRequirementsLevel(String prompt) {
		
		switch(prompt) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "10":
			
			gui.getSend().setEnabled(false);
			
			userInputs.add(prompt);
			
			String idea = userInputs.get(0);
			String type = userInputs.get(1);
			String features = userInputs.get(2);
			String users = userInputs.get(3);
			String tech = userInputs.get(4);
			String level = userInputs.get(5);

			if(features.equals("skip")) {
				if(users.equals("skip"))
					prompt = "Imagine that you are a programming tutor, I want to build a coding project, " + idea + ", the type of the project is a " + type + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", give me the functional requirements of this project, the requirements should be detailed and realistic, this is an example of a requirement format: As [a user persona], I want [to perform this action] so that [I can accomplish this goal], the requirements should be ordered in the order of implementation.";
				else
					prompt = "Imagine that you are a programming tutor, I want to build a coding project, " + idea + ", the type of the project is a " + type + ", the users of the project are: " + users + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", give me the functional requirements of this project, the requirements should be detailed and realistic, this is an example of a requirement format: As [a user persona], I want [to perform this action] so that [I can accomplish this goal], the requirements should be ordered in the order of implementation.";
			} else {
				if(users.equals("skip"))
					prompt = "Imagine that you are a programming tutor, I want to build a coding project, " + idea + ", the type of the project is a " + type + ", some essential features of the project are: " + features + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", give me the functional requirements of this project, the requirements should be detailed and realistic, this is an example of a requirement format: As [a user persona], I want [to perform this action] so that [I can accomplish this goal], the requirements should be ordered in the order of implementation.";
				else
					prompt = "Imagine that you are a programming tutor, I want to build a coding project, " + idea + ", the type of the project is a " + type + ", the users of the project are: " + users + ", some essential features of the project are: " + features + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", give me the functional requirements of this project, the requirements should be detailed and realistic, this is an example of a requirement format: As [a user persona], I want [to perform this action] so that [I can accomplish this goal], the requirements should be ordered in the order of implementation.";
			}
			
			chat.add(prompt);
			
			String response = ChatGPT.callAPI(chat);
			chat.add(response);
			displaySlimResponse(response);
			
			String text = "Now, you can write me your follow up messages. \nYou have my full attention!";
			displaySlimResponse(text);
			
			this.state = "write prompts";
			
		    gui.getSend().setEnabled(true);
			
			break;
		default:
			String text1 = "Please choose a number between 1 and 10.";
			displaySlimResponse(text1);
		}
		
	}

	protected void projectsRequirementsTech(String prompt) {
		
		userInputs.add(prompt);
		String text = "What is your level of experience in " + prompt + " on a scale of 1 to 10? \n(provide me with a number)\n"
		           + "1 = you know nothing \n"
		           + "10 = you know everything";
		displaySlimResponse(text);
		this.state = "projects requirements level";
		
	}

	protected void projectsRequirementsUsers(String prompt) {
		
		userInputs.add(prompt);
		String text = "Which technology or programming language you will use to build the project?";
		displaySlimResponse(text);
		this.state = "projects requirements tech";
		
	}

	protected void projectsRequirementsFeatures(String prompt) {
		
		userInputs.add(prompt);
		String text = "Who are the users of the project or application? (e.g. admin, client, ...)\n"
				+ "(provide me with the users or \"skip\")";
		displaySlimResponse(text);
		this.state = "projects requirements users";
		
	}

	protected void projectsRequirementsType(String prompt) {
	
		userInputs.add(prompt);
		String text = "What features are essential to you in the project? \n(provide me with the features or \"skip\")";
		displaySlimResponse(text);
		this.state = "projects requirements features";
		
	}

	protected void projectsRequirementsIdea(String prompt) {
		
		userInputs.add(prompt);
		String text = "What is the type of the project you want to build? (e.g. web app or mobile app ...)";
		displaySlimResponse(text);
		this.state = "projects requirements type";
		
	}

	protected void projectsIdeasDomain(String prompt) {
	
		gui.getSend().setEnabled(false);
		
		userInputs.add(prompt);
		
		String type = userInputs.get(0);
		String tech = userInputs.get(1);
		String level = userInputs.get(2);
		String domain = userInputs.get(3);

		if(domain.equals("skip"))
			prompt = "Imagine that you are a programming tutor, give me 20 ideas for coding projects, specifically, the type of the project should be " + type + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ".";
		else
			prompt = "Imagine that you are a programming tutor, give me 20 ideas for coding projects, specifically, the type of the project should be " + type + ", and the project should be related to " + domain + ", I plan to build the project using " + tech + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ".";
		
		chat.add(prompt);
		
		String response = ChatGPT.callAPI(chat);
		chat.add(response);
		displaySlimResponse(response);
		
		String text = "Now, you can write me your follow up messages. \nYou have my full attention!";
		displaySlimResponse(text);
		
		this.state = "write prompts";
		
	    gui.getSend().setEnabled(true);
	
	}

	protected void projectsIdeasLevel(String prompt) {
		
		switch(prompt) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "10":
			
			userInputs.add(prompt);
			String text = "Do you have any preferences for the domain or industry your project relates to? \n(e.g. finance, healthcare, education) \n"
					   + "(provide me with the domain or \"skip\")";
			displaySlimResponse(text);
			this.state = "projects ideas domain";
			
			break;
		default:
			String text1 = "Please choose a number between 1 and 10.";
			displaySlimResponse(text1);
		}
		
	}

	protected void projectsIdeasTech(String prompt) {

		userInputs.add(prompt);
		String text = "What is your level of experience in " + prompt + " on a scale of 1 to 10? \n(provide me with a number)\n"
		           + "1 = you know nothing \n"
		           + "10 = you know everything";
		displaySlimResponse(text);
		this.state = "projects ideas level";
		
	}

	protected void projectsIdeasType(String prompt) {
		
		userInputs.add(prompt);
		String text = "Which technology or programming language you want to use to build the project?";
		displaySlimResponse(text);
		this.state = "projects ideas tech";
		
	}

	protected void projectsIdeasOrRequirements(String prompt) {
		
		String text = "";
		
		switch(prompt) {
		case "1":
			
			text = "What is the type of project you have in mind? (e.g. web app or mobile app ...)";
			displaySlimResponse(text);
			this.state = "projects ideas type";
			
			break;
			
		case "2":
			
			text = "What is the main idea of your project?";
			displaySlimResponse(text);
			this.state = "projects requirements idea";
			
			break;
			
		default:
			text = "I am sorry, \nbut this is not one of the options, \nplease choose from the options I mentioned earlier.";
			displaySlimResponse(text);
		}
		
	}

	protected void problemSolvingSolution(String prompt) {
		
		gui.getSend().setEnabled(false);
		
		prompt = "I will provide you with my solution to the problem, explain whether it is correct or not in details, also comment on the efficiency of my code, If my solution is not correct give me hints to help me construct a correct solution, finally, rate my solution out of 10, this is my solution: ``` " + prompt + " ``` .";

		chat.add(prompt);
		
		String response = ChatGPT.callAPI(chat);
		chat.add(response);
		displaySlimResponse(response);
		
		String text = "Now, you can write me your follow up messages. \nYou have my full attention!";
		displaySlimResponse(text);
		
		this.state = "write prompts";
		
	    gui.getSend().setEnabled(true);
	    
	}

	protected void problemSolvingDifficulty(String prompt) {
		
		switch(prompt) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "10":
			
			gui.getSend().setEnabled(false);
			
			userInputs.add(prompt);
			
			String tech = userInputs.get(0);
			String topic = userInputs.get(1);
			String difficulty = userInputs.get(2);
			
			prompt = "Imagine that you are a programming tutor, give me a programming problem so that I attempt to solve, I plan to solve it using " + tech + ", the problem should focus on " + topic + ", the difficulty of the problem on a scale of 1 to 10 (1 = extremely easy, 10 = extremely hard) should be " + difficulty + ", the problem statement should be clear and detailed, also you should provide me with examples of different inputs and their expected outputs, do not give me the solution unless I tell you to do that.";
			
			chat.add(prompt);
			
			String response = ChatGPT.callAPI(chat);
			chat.add(response);
			displaySlimResponse(response);
			
			String text = "Please provide me with your solution to be reviewed:";
			displaySlimResponse(text);
			
			this.state = "problem solving solution";
			
		    gui.getSend().setEnabled(true);
				
			break;
		default:
			String text1 = "Please choose a number between 1 and 10.";
			displaySlimResponse(text1);
		}
		
	}

	protected void problemSolvingTopic(String prompt) {
		
		userInputs.add(prompt);
		String text = "On a scale of 1 to 10, what level of difficulty would you like the problem to be? \n(provide me with a number)\n"
		           + "1 = extremely easy \n"
		           + "10 = extremely hard";
		displaySlimResponse(text);
		this.state = "problem solving difficulty";
		
	}

	protected void problemSolvingTech(String prompt) {
		
		userInputs.add(prompt);
		String text = "What topic would you like the problem to focus on?";
		displaySlimResponse(text);
		this.state = "problem solving topic";
		
	}

	protected void quizzesSolution(String prompt) {
		
		String text = "";
		
		switch(prompt) {
		case "1":
			
			gui.getSend().setEnabled(false);
			
			prompt = "Give me all the correct answers of the previous questions that you gave me, also explain why it is the correct answer";
	
			chat.add(prompt);
			
			String response = ChatGPT.callAPI(chat);
			chat.add(response);
			displaySlimResponse(response);
			
			text = "Now, you can write me your follow up messages. \nYou have my full attention!";
			displaySlimResponse(text);
			
			this.state = "write prompts";
			
		    gui.getSend().setEnabled(true);
			
			break;
			
		case "2":
			
			text = "Now, you can write me your follow up messages. \nYou have my full attention!";
			displaySlimResponse(text);
			
			this.state = "write prompts";
			
			break;
			
		default:
			text = "I am sorry, \nbut this is not one of the options, \nplease choose from the options I mentioned earlier.";
			displaySlimResponse(text);
		}
		
	}

	protected void quizzesNoOfQuestions(String prompt) {
		
		if(isPositiveInteger(prompt)) {
			
			gui.getSend().setEnabled(false);
			
			userInputs.add(prompt);
			
			String tech = userInputs.get(0);
			String topic = userInputs.get(1);
			String difficulty = userInputs.get(2);
			String type = userInputs.get(3);
			String noOfQuestions = userInputs.get(4);
			
			switch(type) {
	        case "1":
	        	prompt = "Imagine that you are a programming tutor, give me " + noOfQuestions + " multiple choice questions, the difficulty of the questions on a scale of 1 to 10 (1 is the easiest, 10 is the hardest) should be " + difficulty + ", the topic of the questions is " + topic + ", and you should use " + tech + " in any code provided in the questions, do not give me the answers of the questions unless I ask you to do that.";
	        	break;
	        case "2":
	        	prompt = "Imagine that you are a programming tutor, give me " + noOfQuestions + " true or false questions, the difficulty of the questions on a scale of 1 to 10 (1 is the easiest, 10 is the hardest) should be " + difficulty + ", the topic of the questions is " + topic + ", and you should use " + tech + " in any code provided in the questions, do not give me the answers of the questions unless I ask you to do that.";
	        	break;
	        case "3":
	        	prompt = "Imagine that you are a programming tutor, give me " + noOfQuestions + " code tracing questions, the difficulty of the questions on a scale of 1 to 10 (1 is the easiest, 10 is the hardest) should be " + difficulty + ", the topic of the questions is " + topic + ", and you should use " + tech + " in any code provided in the questions, do not give me the answers of the questions unless I ask you to do that.";
	        	break;
	        case "4":
	        	prompt = "Imagine that you are a programming tutor, give me " + noOfQuestions + " questions, the type of questions should be a mix between multiple choice, true or false, and code tracing questions, the difficulty of the questions on a scale of 1 to 10 (1 is the easiest, 10 is the hardest) should be " + difficulty + ", the topic of the questions is " + topic + ", and you should use " + tech + " in any code provided in the questions, do not give me the answers of the questions unless I ask you to do that.";
	        	break;
	        }
			
			chat.add(prompt);
			
			String response = ChatGPT.callAPI(chat);
			chat.add(response);
			displaySlimResponse(response);
			
			String text = "Do you want me to provide you with the quiz solution now? (enter \"1\" or \"2\") \n"
					+ "1 = yes \n"
					+ "2 = no";
			displaySlimResponse(text);
			
			this.state = "quizzes solution";
			
		    gui.getSend().setEnabled(true);
			
		} else {
			String text1 = "Please provide me with a valid number of hours.";
			displaySlimResponse(text1);
		}
		
	}

	public static boolean isPositiveInteger(String input) {
        // Check if the input string is not null or empty
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        // Check if the string consists only of digits
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        
        // Convert the string to an integer and check if it's greater than zero
        int num = Integer.parseInt(input);
        return num > 0;
    }
	
	protected void quizzesType(String prompt) {
		
		switch(prompt) {
		case "1":
		case "2":
		case "3":
		case "4":
			userInputs.add(prompt);
			String text = "Please choose the number of questions of the quiz: (provide me with a number)";
			displaySlimResponse(text);
			this.state = "quizzes no of questions";
			break;
		default:
			String text1 = "I am sorry, \nbut this is not one of the options, \nplease choose from the options I mentioned earlier.";
			displaySlimResponse(text1);
		}
		
	}

	protected void quizzesDifficulty(String prompt) {
		
		switch(prompt) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "10":
			userInputs.add(prompt);
			String text = "Please choose the type of questions in the quiz: (provide me with the option number) \n"
			           + "1 = multiple choice \n"
			           + "2 = true or false \n"
			           + "3 = code tracing \n"
			           + "4 = mix";
			displaySlimResponse(text);
			this.state = "quizzes type";
			break;
		default:
			String text1 = "Please choose a number between 1 and 10.";
			displaySlimResponse(text1);
		}
		
	}

	protected void quizzesTopic(String prompt) {
		
		userInputs.add(prompt);
		String text = "On a scale of 1 to 10, what level of difficulty would you like the quiz questions to be? \n(provide me with a number)\n"
		           + "1 = extremely easy \n"
		           + "10 = extremely hard";
		displaySlimResponse(text);
		this.state = "quizzes difficulty";
		
	}

	protected void quizzesTech(String prompt) {
		
		userInputs.add(prompt);
		String text = "What is the topic you want to be assessed on?";
		displaySlimResponse(text);
		this.state = "quizzes topic";
		
	}

	protected void explainCodeLines(String prompt) {
		
		gui.getSend().setEnabled(false);
		
		userInputs.add(prompt);
		
		String code = userInputs.get(0);
		String tech = userInputs.get(1);
		String level = userInputs.get(2);
		String context = userInputs.get(3);
		String focus = userInputs.get(4);
		
		if(focus.equals("skip"))
			prompt = "Imagine that you are a programming tutor, I will give you a piece of code written in " + tech + ", and I want you to explain it to me in details, line by line: " + code + " the code is written in the following context: " + context + ", my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", also tell me -if possible- the output or result of this code, finally, tell me if there are prerequisites to understand this code.";
		else
			prompt = "Imagine that you are a programming tutor, I will give you a piece of code written in " + tech + ", and I want you to explain it to me in details: " + code + " focus in your explanation on the following lines of code: " + focus + " , the code is written in the following context: " + context + ", my knowledge level on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", also tell me -if possible- the output or result of this code, finally, tell me if there are prerequisites to understand this code.";
	
		chat.add(prompt);
		
		String response = ChatGPT.callAPI(chat);
		chat.add(response);
		displaySlimResponse(response);
		
		String text = "Now, you can write me your follow up messages. \nYou have my full attention!";
		displaySlimResponse(text);
		
		this.state = "write prompts";
		
	    gui.getSend().setEnabled(true);
			
	}

	protected void explainCodeContext(String prompt) {
		
		userInputs.add(prompt);
		String text = "Do you want the explanation to focus on specific lines of the code? \n(provide me with the line numbers or \"skip\")";
		displaySlimResponse(text);
		this.state = "explain code lines";	
		
	}

	protected void explainCodeLevel(String prompt) {
		
		switch(prompt) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "10":
			userInputs.add(prompt);
			String text = "What is the context in which this code was written?";
			displaySlimResponse(text);
			this.state = "explain code context";
			break;
		default:
			String text1 = "Please choose a number between 1 and 10.";
			displaySlimResponse(text1);
		}
		
	}

	protected void explainCodeTech(String prompt) {
		
		userInputs.add(prompt);
		String text = "What is your level of experience in " + prompt + " on a scale of 1 to 10? (provide me with a number) \n"
		           + "1 = you know nothing \n"
		           + "10 = you know everything";
		displaySlimResponse(text);
		this.state = "explain code level";
		
	}

	protected void explainCodeCode(String prompt) {
		
		prompt = " ``` " + prompt + " ``` ";
		userInputs.add(prompt);
		String text = "What is the technology or programming language used in this code?";
		displaySlimResponse(text);
		this.state = "explain code tech";
		
	}

//	protected void summarizeTutorialsAspect(String prompt) {
//		
//		gui.getSend().setEnabled(false);
//		
//		userInputs.add(prompt);
//		
//		if(userInputs.get(0).equals("1")) {
//			
//			String link = userInputs.get(1);
//			String focus = userInputs.get(2);
//			
//			if(focus.equals("skip"))
//				prompt = "Imagine that you are a programming tutor" + ", I want you to summarize this article for me, the summary should be detailed and long, while being less in size than the original article, give me the summary in bullet points, this is the article link: " + link + " , also, tell me if there are prerequisites to understand this article, finally, give me a link to a similar article.";
//			else
//				prompt = "Imagine that you are a programming tutor" + ", I want you to summarize this article for me, the summary should be detailed and long, while being less in size than the original article, give me the summary in bullet points, this is the article link: " + link + " . focus in your summary on " + focus + ", also, tell me if there are prerequisites to understand this article, finally, give me a link to a similar article.";
//			
//			
//		} else {
//			
//			String title = userInputs.get(1);
//			String channel = userInputs.get(2);
//			String focus = userInputs.get(3);
//			
//			if(focus.equals("skip"))
//				prompt = "Imagine that you are a programming tutor" + ", I want you to summarize this YouTube video for me, this is its title: " + title + " by " + channel + " channel, the summary should be detailed and long, while being less in size than the original video, also, tell me if there are prerequisites to understand this video, finally, give me a link to a similar YouTube video.";
//			else
//				prompt = "Imagine that you are a programming tutor" + ", I want you to summarize this YouTube video for me, this is its title: " + title + " by " + channel + " channel, focus in your summary on " + focus + ", the summary should be detailed and long, while being less in size than the original video, also, tell me if there are prerequisites to understand this video, finally, give me a link to a similar YouTube video.";
//				
//		}
//		
//		chat.add(prompt);
//		
//		String response = ChatGPT.callAPI(chat);
//		chat.add(response);
//		displaySlimResponse(response);
//		
//		String text = "Now, you can write me your follow up messages. \nYou have my full attention!";
//		displaySlimResponse(text);
//		
//		this.state = "write prompts";
//		
//	    gui.getSend().setEnabled(true);
//		
//	}
//
//	protected void summarizeTutorialsVideoTitle(String prompt) {
//		
//		userInputs.add(prompt);
//		String text = "Please provide me with the name of the channel that published the video:";
//		displaySlimResponse(text);
//		this.state = "summarize tutorials article link or video channel";
//		
//	}
//
//	protected void summarizeTutorialsArticleLinkOrVideoChannel(String prompt) {
//		
//		userInputs.add(prompt);
//		String text = "Do you want the summary to focus on a specific aspect of the tutorial? \n(provide me with the aspect or \"skip\")";
//		displaySlimResponse(text);
//		this.state = "summarize tutorials aspect";
//		
//	}
//
//	protected void summarizeTutorialsType(String prompt) {
//		
//		String text = "";
//		
//		switch(prompt) {
//		case "1":
//			
//			userInputs.add(prompt);
//			
//			text = "Please provide me with the article link:";
//			displaySlimResponse(text);
//			
//			this.state = "summarize tutorials article link or video channel";
//			
//			break;
//			
//		case "2":
//			
//			userInputs.add(prompt);
//					
//			text = "Please provide me with the YouTube video title:";
//			displaySlimResponse(text);
//			
//			this.state = "summarize tutorials video title";
//			
//			break;
//			
//		default:
//			text = "I am sorry, \nbut this is not one of the options, \nplease choose from the options I mentioned earlier.";
//			displaySlimResponse(text);
//		}
//		
//	}

	protected void codingConceptsLevel(String prompt) {
		
		switch(prompt) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "10":
			
			gui.getSend().setEnabled(false);
			
			userInputs.add(prompt);
			
			String concept = userInputs.get(0);
			String usage = userInputs.get(1);
			String tech = userInputs.get(2);
			String level = userInputs.get(3);
			
			if(usage.equals("skip"))
				prompt = "Imagine that you are a programming tutor, explain to me " + concept + " in details, use " + tech + " in your explanation, my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", include practical coding examples in your explanation, also tell me if there are prerequisites to understand " + concept + " well, finally, provide me with links to resources explaining this topic for more information.";
			else
				prompt = "Imagine that you are a programming tutor, explain to me " + concept + " in details, I want to learn " + concept + " to use it in " + usage + ", use " + tech + " in your explanation, my level of experience on a scale of 1 to 10 (1 = knows nothing, 10 = knows everything) in " + tech + " is " + level + ", include practical coding examples in your explanation, also tell me if there are prerequisites to understand " + concept + " well, finally, provide me with links to resources explaining this topic for more information.";
			
			chat.add(prompt);
			
			String response = ChatGPT.callAPI(chat);
			chat.add(response);
			displaySlimResponse(response);
			
			String text = "Now, you can write me your follow up messages. \nYou have my full attention!";
			displaySlimResponse(text);
			
			this.state = "write prompts";
			
		    gui.getSend().setEnabled(true);
		    
			break;
		default:
			String text1 = "Please choose a number between 1 and 10.";
			displaySlimResponse(text1);
		}
		
	}

	protected void codingConceptsTech(String prompt) {
		
		userInputs.add(prompt);
		String text = "What is your level of experience in " + userInputs.get(2) + " on a scale of 1 to 10? (provide me with a number) \n"
		           + "1 = you know nothing \n"
		           + "10 = you know everything";
		displaySlimResponse(text);
		this.state = "coding concepts level";
		
	}

	protected void codingConceptsUsage(String prompt) {
		
		userInputs.add(prompt);
		String text = "Which technology or programming language do you prefer to use to explain " + userInputs.get(0) + "?";
		displaySlimResponse(text);
		this.state = "coding concepts tech";
		
	}

	protected void codingConceptsConcept(String prompt) {
		
		userInputs.add(prompt);
		String text = "Do you want to learn " + prompt + " for a specific usage? \n(provide me with the usage or \"skip\")";
		displaySlimResponse(text);
		this.state = "coding concepts usage";
		
	}

	protected void studyPlansNoOfHours(String prompt) {
		
		if(isPositiveNumber(prompt)) {
			
			gui.getSend().setEnabled(false);
			
			userInputs.add(prompt);
			
			String tech = userInputs.get(0);
			String level = userInputs.get(1);
			String learningType = userInputs.get(2);
			String paidResources = userInputs.get(3);
			String hoursPerWeek = userInputs.get(4);
			
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
			
			chat.add(prompt);
			
			String response = ChatGPT.callAPI(chat);
			chat.add(response);
			displaySlimResponse(response);
			
			String text = "Now, you can write me your follow up messages. \nYou have my full attention!";
			displaySlimResponse(text);
			
			this.state = "write prompts";
			
		    gui.getSend().setEnabled(true);
			
		} else {
			String text1 = "Please provide me with a valid number of hours.";
			displaySlimResponse(text1);
		}
		
	}

	protected void studyPlansPaidResources(String prompt) {
		
		switch(prompt) {
		case "1":
		case "2":
			userInputs.add(prompt);
			String text = "How many hours per week can you dedicate for studying? (enter a number)";
			displaySlimResponse(text);
			this.state = "study plans no of hours";
			break;
		default:
			String text1 = "I am sorry, \nbut this is not one of the options, \nplease choose from the options I mentioned earlier.";
			displaySlimResponse(text1);
		}
		
	}

	public static boolean isPositiveNumber(String str) {
        // Check if the string is not empty
        if (str != null && !str.isEmpty()) {
            try {
                // Parse the string to a double
                double num = Double.parseDouble(str);
                
                // Check if the number is positive
                if (num > 0) {
                    return true;
                }
            } catch (NumberFormatException e) {
                // If parsing fails, it's not a number
                return false;
            }
        }
        // If the string is empty or not a positive number, return false
        return false;
    }
	
	protected void studyPlansLearningType(String prompt) {
		
		switch(prompt) {
		case "1":
		case "2":
			userInputs.add(prompt);
			String text = "Do you have a problem with paid resources? (enter \"1\" or \"2\") \n"
					+ "1 = yes \n"
					+ "2 = no";
			displaySlimResponse(text);
			this.state = "study plans paid resources";
			break;
		default:
			String text1 = "I am sorry, \nbut this is not one of the options, \nplease choose from the options I mentioned earlier.";
			displaySlimResponse(text1);
		}
	
	}

	protected void studyPlansLevel(String prompt) {
		
		switch(prompt) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "10":
			userInputs.add(prompt);
			String text = "Do you prefer learning by reading or watching? (enter \"1\" or \"2\") \n"
					+ "1 = reading \n"
					+ "2 = watching";
			displaySlimResponse(text);
			this.state = "study plans learning type";
			break;
		default:
			String text1 = "Please choose a number between 1 and 10.";
			displaySlimResponse(text1);
		}
		
	}

	protected String displayUserMessage() {
		
		String prompt = gui.getPrompt().getText();
		gui.getPrompt().setText("");
		
		String text = "[You]: " + prompt + "\n\n";
		gui.getChat().append(text);
		
		return prompt;
		
	}

	protected void studyPlansTech(String prompt) {
		
		userInputs.add(prompt);
		String text = "What is your level of experience in " + prompt + " on a scale of 1 to 10? (provide me with a number) \n"
				+ "1 = you know nothing \n"
				+ "10 = you know everything";
		displaySlimResponse(text);
		this.state = "study plans level";
		
	}

	protected void actionPerformedWritePrompts(String prompt) {
		
		gui.getSend().setEnabled(false);
		
		chat.add(prompt);
		
		String response = ChatGPT.callAPI(chat);
		displaySlimResponse(response);
		chat.add(response);
	    
	    gui.getSend().setEnabled(true);
		
	}

	private void displaySlimResponse(String response) {

		String text = "[Slim]: " + response + "\n\n";
		gui.getChat().append(text);
		
	}

	private void addNewChatButtonActionListener() {
		
		gui.getNewChat().addActionListener(e -> {
			
			chat.clear();
			
			userInputs.clear();
			
			gui.getPrompt().setText("");
			
			gui.getChat().setText("");
			
			state = "start chat";
			
			startChat();
			
		});
		
	}

	private void startChat() {
		
		String text = "How can I help you? \n"
				    + "Please choose an option: (provide me with the option number) \n"
		            + "1 = Study plans \n"
		            + "2 = Coding concepts \n"
		            //+ "3 = Summarize tutorials \n"
		            + "3 = Explain code \n"
		            + "4 = Quizzes \n"
		            + "5 = Problem solving \n"
		            + "6 = Projects \n"
		            + "7 = Code reviews \n"
		            + "8 = Fix errors \n"
		            + "9 = Generate code \n"
		            + "10 = Write your own messages";
	
		displaySlimResponse(text);
		
		if(this.isAppJustRan) {
			gui.show();
			this.isAppJustRan = false;
		}
			
	}

	private void router(String userInput) {
		
		switch(userInput) {
		case "1": studyPlans(); break;
		case "2": codingConcepts(); break;
		//case "3": summarizeTutorials(); break;
		case "3": explainCode(); break;
		case "4": quizzes(); break;
		case "5": problemSolving(); break;
		case "6": projects(); break;
		case "7": codeReviews(); break;
		case "8": fixErrors(); break;
		case "9": generateCode(); break;
		case "10": writePrompts(); break;
		default: 
			String text = "I am sorry, \nbut this is not one of the options, \nplease choose from the options I mentioned earlier.";
			displaySlimResponse(text);
		}
		
	}
	
	private void quizzes() {
		  
		String text = "Which technology or programming language do you prefer to use in the quiz?";
		displaySlimResponse(text);
		this.state = "quizzes tech";
			
	}

//	private void summarizeTutorials() {
//		
//		String text = "Do you want to summarize an article or a YouTube video? (enter \"1\" or \"2\") \n"
//				+ "1 = article \n"
//				+ "2 = video";
//		displaySlimResponse(text);
//		this.state = "summarize tutorials type";
//	
//	}

	private void writePrompts() {
		
		String text = "Now, you can write me your own messages. \nYou have my full attention!";
		displaySlimResponse(text);
		this.state = "write prompts";
		
	}

	private void generateCode() {
		
		String text = "Which programming language would you like the generated code to be written in?";
		displaySlimResponse(text);
		this.state = "generate code tech";
		
	}

	private void explainCode() {
		  
		String text = "Please provide me with the code you want to be explained:";
		displaySlimResponse(text);
		this.state = "explain code code";
			
	}

	private void fixErrors() {

		String text = "Please provide me with the code that has the error:";
		displaySlimResponse(text);
		this.state = "fix errors code";
		
	}

	private void codeReviews() {
		
		String text = "Please provide me with the code you want to be reviewed:";
		displaySlimResponse(text);
		this.state = "code reviews code";
		
	}

	private void projects() {
		
		String text = "You want to build a project! \n"
		         + "I can help you choose a project idea, \n"
		         + "or provide you with requirements for the idea you have in mind. \n(choose an option, enter \"1\" or \"2\")\n"
		         + "1 = project ideas \n"
		         + "2 = project requirements";
		displaySlimResponse(text);
		this.state = "projects ideas or requirements";
		
	}

	private void problemSolving() {
		
		String text = "Which programming language do you prefer to use in the problem?";
		displaySlimResponse(text);
		this.state = "problem solving tech";
		
	}

	private void codingConcepts() {
		
		String text = "What is the programming concept you want me to explain?";
		displaySlimResponse(text);
		this.state = "coding concepts concept";
        
	}

	private void studyPlans() {
		
		String text = "Which technology or programming language do you plan to study?";
		displaySlimResponse(text);
		this.state = "study plans tech";
		
	}
	
}