package br.ce.wcaquino.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() throws MalformedURLException {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.107:4444/wd/hub"), cap);
        driver.navigate().to("http://192.168.0.107:8001/tasks");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws MalformedURLException {
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
    public void naodeveSalvarTarefaSemDescricao() throws MalformedURLException {
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
    public void naodeveSalvarTarefaSemData() throws MalformedURLException {
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
    public void naodeveSalvarTarefaComDataPassada() throws MalformedURLException {
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


    @Test
    public void deveRemoverTarefaComSucesso() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try {
            // Inserir tarefa
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
            driver.findElement(By.id("saveButton")).click();
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);

            // Remover tarefa
            driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
            message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);
        } finally {
            // Fechar browser
            driver.quit();
        }
    }

}
