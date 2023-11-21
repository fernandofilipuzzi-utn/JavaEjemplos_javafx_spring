package ar.com.hdcm.catalogoLibros.prueba;

public class Person {
	  private String firstName;
	  private String lastName;
	  private String job;

	  public Person(String firstName, String lastName, String job) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.job = job;
	  }

	  public String getFirstName() {
	    return firstName;
	  }

	  public void setFirstName(String firstName) {
	    this.firstName = firstName;
	  }

	  public String getLastName() {
	    return lastName;
	  }

	  public void setLastName(String lastName) {
	    this.lastName = lastName;
	  }

	  public String getJob() {
	    return job;
	  }

	  public void setJob(String job) {
	    this.job = job;
	  }

}
