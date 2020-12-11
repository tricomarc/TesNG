package pruebas;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckingLinks {

	private WebDriver  driver;
	
	public  CheckingLinks(WebDriver driver) {
		
		this.driver =  driver;
	}

	public boolean checkingLinkPage() {

		List<WebElement> links = driver.findElements(By.tagName("a"));
		String url = "";
		List<String> brokenList = new ArrayList<String>();
		List<String> okList = new ArrayList<String>();
		List<String> vacias =  new ArrayList<String>();
		HttpURLConnection httpConnection = null;
		int responseCode = 200;

		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println(url + "URL no esta configurada o está vacía");
				vacias.add(url);
				continue;
			}

			try {
				httpConnection = (HttpURLConnection) (new URL(url).openConnection());
				httpConnection.setRequestMethod("HEAD");
				httpConnection.connect();
				responseCode = httpConnection.getResponseCode();

				if (responseCode > 400) {
					System.out.println("Error Broken Link: -- " + url);
					brokenList.add(url);
				} else if(responseCode == 200) {
					System.out.println("Link Valido: -- " + url);
					okList.add(url);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		System.out.println("Links Válidos : " + okList.size());
		System.out.println("Links Inválidos : " + brokenList.size());
		System.out.println("Vacíos : " + vacias.size());
		if (brokenList.size() > 0) {

			System.out.println("****************ERROR******************** LINKS INVÁLIDOS");
			for (int i = 0; i < brokenList.size(); i++) {
				System.out.println(brokenList.get(i));
			}

			return false;
		} else {
			return true;
		}

	}
}
