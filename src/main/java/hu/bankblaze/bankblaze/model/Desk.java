package hu.bankblaze.bankblaze.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Desk {
    @Id
    private Long id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "queue_number_id")
    private QueueNumber queueNumber;
}
