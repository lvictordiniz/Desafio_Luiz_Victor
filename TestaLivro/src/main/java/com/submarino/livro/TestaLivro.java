/**
 * Codigo 
 * @author Larissa Silva <larissa.natali00@gmail.com>
 */
package com.submarino.livro;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestaLivro {
	
	public WebDriver driver;
	private FluentWait<WebDriver> wait;
	public String nomeautorsub;
	public String numISBNsub;
	
	@Before
	public void setUp() throws Exception
	{
		//SetUp do Google Chorme
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void SiteSubmarino()  {
	
	//Acessar Site Submarino
	driver.get("https://www.submarino.com.br/");
	//Procura livro 
	wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Livros")));
	driver.findElement(By.id("h_search-input")).sendKeys("Livros");
	driver.findElement(By.id("h_search-btn")).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Livro - O Milagre da Manhã")));
	//Seleciona primeiro livro 
	driver.findElement(By.xpath("//*[@id=\"content-middle\"]/div[5]/div/div/div/div[1]/div[2]/div/div[2]/a/section/div[2]/div[1]")).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"info-section\"]/div[2]/section/div/div[3]/table/tbody/tr[13]/td[2]/span")));
	// Verifica o nome do autor 
	WebElement autorsub = driver.findElement(By.xpath("//*[@id=\"info-section\"]/div[2]/section/div/div[3]/table/tbody/tr[13]/td[2]/span"));
	nomeautorsub = autorsub.getText();
	System.out.println("Site Submarino - Nome do Autor: " + nomeautorsub);
	//Salva o número da ISBN
	WebElement ISBN = driver.findElement(By.xpath("//*[@id=\"info-section\"]/div[2]/section/div/div[3]/table/tbody/tr[12]/td[2]/span"));
	numISBNsub = ISBN.getText();
	System.out.println("Site Submarino - Numero ISBN: " + numISBNsub);
		
	}
	public void SiteAmericanas() {
	//Acessar Site Americanas
	driver.get("https://www.americanas.com.br/");
	//Procura pelo ISBN
	driver.findElement(By.id("h_search-input")).sendKeys(numISBNsub);
	driver.findElement(By.id("h_search-btn")).click();
	//Procura pelo nome do autor 
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content-middle\"]/div[5]/div/div/div/div[1]/div/div/div[2]/a/section/div[2]/div[1]/h1")));
	driver.findElement(By.xpath("//*[@id=\"content-middle\"]/div[5]/div/div/div/div[1]/div/div/div[2]/a/section/div[2]/div[1]/h1")).click();
	//Valida se o nome do autor esta correto. 
	WebElement autoran = driver.findElement(By.xpath("//*[@id=\"info-section\"]/div[2]/section/div/div[3]/table/tbody/tr[13]/td[2]/span"));
	String nomeautoran = autoran.getText();
	Assert.assertEquals(nomeautorsub, nomeautoran);
	System.out.println("Site Americanas - Nome do Autor está correto: " + nomeautoran);
	//Fecha Navegador
	}
	
	public void SiteAmazon() throws Exception {
	//Acessar Amazon
	driver.get("https://www.amazon.com.br/");
	//Procura pelo ISBN
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys("9788576849940");
	driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();
	//Seleciona livro 
	driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div/div/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span")).click();
	//Procura pelo nome do autor
	WebElement nomeautoraz = driver.findElement(By.xpath("//*[@id=\"bylineInfo\"]/span[1]/a"));
	String nomeautoramazon = nomeautoraz.getText();
	Assert.assertEquals(nomeautorsub, nomeautoramazon);
	System.out.println("Site Amazon - Nome do Autor está correto: " + nomeautoramazon);
	
	driver.quit();
	
	}
	
	
	}
	
	
