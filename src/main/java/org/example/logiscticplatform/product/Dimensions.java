package org.example.logiscticplatform.product;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Dimensions {
    @Column(name = "length", nullable = false)
    private BigDecimal  length;
    @Column(name = "width", nullable = false)
    private BigDecimal  width;
    @Column(name = "height", nullable = false)
    private BigDecimal  height;
    public BigDecimal volume(){
        return length
                .multiply(width)
                .multiply(height);
    }
}
