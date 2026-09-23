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

class UpdateFranchiseNameUseCaseTest {

    private final FranchiseRepositoryPort franchiseRepository = mock(FranchiseRepositoryPort.class);
    private final UpdateFranchiseNameUseCase useCase = new UpdateFranchiseNameUseCase(franchiseRepository);

    @Test
    void updatesTheFranchiseName() {
        UUID id = UUID.randomUUID();
        Franchise franchise = new Franchise("Fast Burger");
        when(franchiseRepository.findById(id)).thenReturn(Optional.of(franchise));
        when(franchiseRepository.save(franchise)).thenReturn(franchise);

        Franchise updated = useCase.execute(id, "Fast Burger Co.");

        assertThat(updated.getName()).isEqualTo("Fast Burger Co.");
    }

    @Test
    void failsWhenTheFranchiseDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(franchiseRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(id, "New name")).isInstanceOf(FranchiseNotFoundException.class);
    }
}
