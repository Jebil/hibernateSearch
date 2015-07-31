package hibernateSearch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.Field;

@Entity(name = "author")
@Table(name = "author", schema = "hsearch")
@DynamicUpdate
public class Author {
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "name")
	@Field
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
