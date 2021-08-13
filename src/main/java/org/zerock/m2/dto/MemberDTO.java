package org.zerock.m2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MemberDTO {

    private String mid, mpw, mname, nickname;
    private Timestamp joindate, moddate;

}
