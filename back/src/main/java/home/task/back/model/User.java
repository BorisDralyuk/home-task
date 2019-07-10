package home.task.back.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "users", catalog="task_db")
public class User  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name")
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="group_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
 
    private Group group;
    
    
    @ManyToMany
    @JoinTable(
    		name="user_subscriptions",
    		joinColumns = { @JoinColumn(name = "channel_id") },
    		inverseJoinColumns = { @JoinColumn(name = "subscriber_id") }
    		)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<User> subscribers = new LinkedList<User>();
    

    @ManyToMany
    @JoinTable(
    		name="user_subscriptions",
    		joinColumns = { @JoinColumn(name = "subscriber_id") },
    		inverseJoinColumns = { @JoinColumn(name = "channel_id") }
    		)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<User> subscriptions = new LinkedList<User>();

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<User> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<User> subscribers) {
		this.subscribers = subscribers;
	}

	public List<User> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<User> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", group=" + group + ", subscribers=" + subscribers
				+ ", subscriptions=" + subscriptions + "]";
	} 
	
    
}
