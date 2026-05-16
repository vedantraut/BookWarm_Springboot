package com.vedantraut.bookwarm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardStatsDTO {
	private long totalUsers;
    private long totalBooks;
    private long totalOrders;

}
