package main;

import okhttp3.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChatGPT {
	
	private static final String OPENAI_API_KEY = "your api key here";
	private static final String OPENAI_API_MODEL = "gpt-3.5-turbo";
	
	public static String removeEscapeCharacters(String input) {
        
		input = input.replaceAll("\r", "(backslash r was here)");
		input = input.replaceAll("\n", "(backslash n was here)");
		input = input.replaceAll("\t", "(backslash t was here)");
		input = input.replaceAll("\"", "(backslash double quotes was here)");
		input = input.replaceAll("\b", "(backslash b was here)");
		input = input.replaceAll("\'", "(backslash single quotes was here)");
		input = input.replaceAll("\f", "(backslash f was here)");
		input = input.replaceAll("\\\\", "(backslash backslash was here)");
		
		return input;
		
    }
	
	public static String askChatGPT(String prompt) {
		
		prompt = removeEscapeCharacters(prompt);
		
		 OkHttpClient client = new OkHttpClient.Builder()
		            .connectTimeout(30, TimeUnit.SECONDS) // Set connection timeout
		            .readTimeout(30, TimeUnit.SECONDS) // Set read timeout
		            .build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"model\": \"" + OPENAI_API_MODEL + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}");
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY) // Replace YOUR_API_KEY with your actual OpenAI API key
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            // Parse JSON response
            JSONObject json = new JSONObject(responseData);
            JSONArray choices = json.getJSONArray("choices");
            if (choices.length() > 0) {
                JSONObject message = choices.getJSONObject(0).getJSONObject("message");
                String text = message.getString("content");
                return text;
            } else {
                return "No response from ChatGPT";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
		
	}
	
	public static void askChatGPTPrintResponse(String prompt) {
		
		prompt = removeEscapeCharacters(prompt);
		
		OkHttpClient client = new OkHttpClient.Builder()
	            .connectTimeout(30, TimeUnit.SECONDS) // Set connection timeout
	            .readTimeout(30, TimeUnit.SECONDS) // Set read timeout
	            .build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"model\": \"" + OPENAI_API_MODEL + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}");
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY) // Replace YOUR_API_KEY with your actual OpenAI API key
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            System.out.println(responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static String askChatGPTArray(ArrayList<String> contents) {
		
		String messages = "";
		
		for(int i = 0; i < contents.size(); i++) {
			String content = removeEscapeCharacters(contents.get(i));
			if(i % 2 == 0)
				messages = messages + "{\"role\": \"user\", \"content\": \"" + content + "\"},";
			else
				messages = messages + "{\"role\": \"assistant\", \"content\": \"" + content + "\"},";
		}
			
		messages = messages.substring(0, messages.length() - 1);
		
		 OkHttpClient client = new OkHttpClient.Builder()
		            .connectTimeout(30, TimeUnit.SECONDS) // Set connection timeout
		            .readTimeout(30, TimeUnit.SECONDS) // Set read timeout
		            .build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"model\": \"" + OPENAI_API_MODEL + "\", \"messages\": [" + messages + "]}");
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY) // Replace YOUR_API_KEY with your actual OpenAI API key
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            // Parse JSON response
            JSONObject json = new JSONObject(responseData);
            JSONArray choices = json.getJSONArray("choices");
            if (choices.length() > 0) {
                JSONObject message = choices.getJSONObject(0).getJSONObject("message");
                String text = message.getString("content");
                return text;
            } else {
                return "No response from ChatGPT";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
		
	}
	
	public static void main(String[] args) {
    	
		// askChatGPTPrintResponse("hello");
		
    }
	
}
