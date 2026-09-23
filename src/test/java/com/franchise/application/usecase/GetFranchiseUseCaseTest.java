package com.franchise.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.domain.entity.Franchise;
import com.franchise.domain.exception.FranchiseNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class GetFranchiseUseCaseTest {

    private final FranchiseRepositoryPort franchiseRepository = mock(FranchiseRepositoryPort.class);
    private final GetFranchiseUseCase useCase = new GetFranchiseUseCase(franchiseRepository);

    @Test
    void returnsTheFranchiseWithItsBranchesAndProducts() {
        UUID id = UUID.randomUUID();
        when(franchiseRepository.findByIdWithBranchesAndProducts(id)).thenReturn(Optional.of(new Franchise("Fast Burger")));

        Franchise franchise = useCase.execute(id);

        assertThat(franchise.getName()).isEqualTo("Fast Burger");
    }

    @Test
    void failsWhenTheFranchiseDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(franchiseRepository.findByIdWithBranchesAndProducts(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(id)).isInstanceOf(FranchiseNotFoundException.class);
    }
}
