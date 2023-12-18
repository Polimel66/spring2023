package org.myApplication.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.myApplication.domain.enums.ExchangeState;
import org.myApplication.domain.interfaces.Exchange;

@Entity
@Table(name = "exchanges")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeEntity implements Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "exchange_state")
    @Enumerated(EnumType.STRING)
    private ExchangeState exchangeState;
    @Column(name = "is_exchange_made")
    private boolean isExchangeMade;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "requesting_user_id")
    private UserEntity requestingUser;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "responding_user_id")
    private UserEntity respondingUser;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "requested_book_id")
    private BookEntity requestedBook;
}
