package codigo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.thoughtworks.selenium.Selenium;
import entidades.Conta;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Carregar();
	}

	public static void Carregar() throws InterruptedException {
		List<Conta> listaContas = new ArrayList<Conta>();

		for (int i = 1; i <= 9; i++) {
			Conta conta = new Conta();
			conta.setLogin("login0" + i);
			conta.setEmail("email0" + i + "@hotmail.com.br");
			conta.setSenha("SENHA");
			listaContas.add(conta);
		}

		for (int i = 10; i <= 99; i++) {
			Conta conta = new Conta();
			conta.setLogin("login" + i);
			conta.setEmail("email" + i + "@hotmail.com.br");
			conta.setSenha("SENHA");
			listaContas.add(conta);
		}

		for (Conta contaMigrar : listaContas) {
			Migrar(contaMigrar);
		}
	}

	public static void Migrar(Conta conta) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		op.addArguments("--headless");
		op.addArguments("--disable-gpu");
		WebDriver driver = new ChromeDriver(op);
		System.out.println("INICIO - " + conta.getLogin());
		Selenium selenium = new WebDriverBackedSelenium(driver, "https://migracao.playragnarokonlinebr.com/");
		selenium.open("/login");
		selenium.type("id=email", conta.getLogin());
		selenium.type("id=password", conta.getSenha());
		selenium.click("id=btnLogin");
		TimeUnit.SECONDS.sleep(5);
		selenium.click("id=btnMigrate");
		TimeUnit.SECONDS.sleep(5);
		selenium.click("id=btnMigrate");
		TimeUnit.SECONDS.sleep(5);
		selenium.type("id=Nickname", conta.getLogin());
		selenium.type("id=Email", conta.getEmail());
		selenium.type("id=Password", conta.getSenha());
		selenium.type("id=PasswordConfirm", conta.getSenha());
		driver.findElement(By.xpath("//form/button")).click();
		TimeUnit.SECONDS.sleep(10);
		selenium.close();
		System.out.println("FIM - " + conta.getLogin() + "\n---------------------------------");
	}
}