package com.franchise.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.domain.entity.Franchise;
import java.util.List;
import org.junit.jupiter.api.Test;

class ListFranchisesUseCaseTest {

    private final FranchiseRepositoryPort franchiseRepository = mock(FranchiseRepositoryPort.class);
    private final ListFranchisesUseCase useCase = new ListFranchisesUseCase(franchiseRepository);

    @Test
    void returnsAllFranchises() {
        when(franchiseRepository.findAll()).thenReturn(List.of(new Franchise("Fast Burger"), new Franchise("Pizza Town")));

        List<Franchise> franchises = useCase.execute();

        assertThat(franchises).hasSize(2);
    }
}
