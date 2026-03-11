package org.softsofi.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto{
    
    private Long id ;
    private String name ;
    private String email ;
    
    
}
