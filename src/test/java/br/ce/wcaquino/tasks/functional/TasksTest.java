package br.ce.wcaquino.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao()  {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://192.168.0.107:8001/tasks");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() {
        WebDriver driver = acessarAplicacao();
        try {
            // Clicar em ADD Todo
            driver.findElement(By.id("addTodo")).click();

            // Escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            // Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);
        } finally {
            // Fechar browser
            driver.quit();
        }
    }

    @Test
    public void naodeveSalvarTarefaSemDescricao() {
        WebDriver driver = acessarAplicacao();
        try {
            // Clicar em ADD Todo
            driver.findElement(By.id("addTodo")).click();

            // Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            // Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);
        } finally {
            // Fechar browser
            driver.quit();
        }
    }

    @Test
    public void naodeveSalvarTarefaSemData() {
        WebDriver driver = acessarAplicacao();
        try {
            // Clicar em ADD Todo
            driver.findElement(By.id("addTodo")).click();

            // Escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", message);
        } finally {
            // Fechar browser
            driver.quit();
        }
    }

    @Test
    public void naodeveSalvarTarefaComDataPassada() {
        WebDriver driver = acessarAplicacao();
        try {
            // Clicar em ADD Todo
            driver.findElement(By.id("addTodo")).click();

            // Escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

            // Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);
        } finally {
            // Fechar browser
            driver.quit();
        }
    }

}
