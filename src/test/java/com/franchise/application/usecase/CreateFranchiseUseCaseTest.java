package com.franchise.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.domain.entity.Franchise;
import org.junit.jupiter.api.Test;

class CreateFranchiseUseCaseTest {

    private final FranchiseRepositoryPort franchiseRepository = mock(FranchiseRepositoryPort.class);
    private final CreateFranchiseUseCase useCase = new CreateFranchiseUseCase(franchiseRepository);

    @Test
    void createsAndPersistsANewFranchise() {
        when(franchiseRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Franchise franchise = useCase.execute("Fast Burger");

        assertThat(franchise.getName()).isEqualTo("Fast Burger");
        verify(franchiseRepository).save(any(Franchise.class));
    }
}
