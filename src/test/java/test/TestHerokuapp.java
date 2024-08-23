package test;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TestHerokuapp {

		// VARIABLES
		private WebDriver driver_fox;
		private static final String TIPO_DRIVER = "webdriver.geckodriver.exe";
		private static final String PATH_DRIVER = "./src/test/resources/WebDriver/geckodriver.exe";
		private String URL = "https://the-internet.herokuapp.com/login";
		
		@BeforeClass
		public static void setUpBeforeClass()
		{
			
			System.out.println("INICIO DE LOS TEST");
			System.setProperty(TIPO_DRIVER, PATH_DRIVER);
		}
		
		@Before
		public void setUp()
		{
		driver_fox = new FirefoxDriver();
		driver_fox.manage().window().maximize();
		driver_fox.get(URL);
		
		}
		
		@Test
		public void testSourch01() throws InterruptedException
		{
			//INGRESO DE USUARIO Y CONTRASEÑA VÁLIDOS
			WebElement username = driver_fox.findElement(By.id("username"));
			username.sendKeys("tomsmith", Keys.TAB, "SuperSecretPassword!", Keys.ENTER);
			String id = driver_fox.getWindowHandle();
			System.out.println(id);
			//ESPERAR UN MOMENTO HASTA QUE HEROKUAPP RESPONDA
			Thread.sleep(2000);
			//AL DAR CLIC EN EL BOTÓN CERRAR SESIÓN SE REGRESA A LA PÁGINA ---> The Internet
			driver_fox.findElement(By.xpath("/html/body/div[2]/div/div/a")).click();
			Thread.sleep(2000);
			//AL DAR CLIC EN EL HIPERVÍNCULO SE ABRE LA VENTANA ---> Home | Elemental Selenium
			driver_fox.findElement(By.linkText("Elemental Selenium")).click();
			Thread.sleep(2000);
			//SE ABRE LA VENTANA The Internet
			Set<String> windows= driver_fox.getWindowHandles();
			
			for (String w :windows) {
				driver_fox.switchTo().window(w);
				System.out.println(driver_fox.getTitle());
			}
		}
		
		@Test
		public void testSourch02() throws InterruptedException
		{
			//INGRESO DE USUARIO VÁLIDO Y CONTRASEÑA INVÁLIDA
			WebElement username = driver_fox.findElement(By.id("username"));
			username.sendKeys("tomsmith", Keys.TAB, "Zpaucars!", Keys.ENTER);
			String id = driver_fox.getWindowHandle();
			System.out.println(id);
			//ESPERAR UN MOMENTO HASTA QUE HEROKUAPP RESPONDA
			Thread.sleep(2000);
			//SE MUESTRA LA ETIQUETA EL MENSAJE DE ERROR
			System.out.println("¡Tu contraseña no es válida!");
						
		}
		
		@Test
		public void testSourch03() throws InterruptedException
		{
			//INGRESO DE USUARIO INVÁLIDO Y CONTRASEÑA VÁLIDA
			WebElement username = driver_fox.findElement(By.id("username"));
			username.sendKeys("Zpaucars", Keys.TAB, "SuperSecretPassword!", Keys.ENTER);
			String id = driver_fox.getWindowHandle();
			System.out.println(id);
			//ESPERAR UN MOMENTO HASTA QUE HEROKUAPP RESPONDA
			Thread.sleep(2000);
			//SE MUESTRA LA ETIQUETA EL MENSAJE DE ERROR
			System.out.println("¡Tu nombre de usuario no es válido!");
						
		}
		
		@After
		
		public void tearDown()
		{
			//CIERRA LA PÁGINA
			driver_fox.quit();
			}
		@AfterClass
		public static void tearDownAfterClass()
		{
			System.out.println("FINALIZA LOS TEST");
		}
		
	}

