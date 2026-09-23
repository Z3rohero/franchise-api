package com.franchise.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.franchise.application.port.out.BranchRepositoryPort;
import com.franchise.domain.entity.Branch;
import com.franchise.domain.exception.BranchNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UpdateBranchNameUseCaseTest {

    private final BranchRepositoryPort branchRepository = mock(BranchRepositoryPort.class);
    private final UpdateBranchNameUseCase useCase = new UpdateBranchNameUseCase(branchRepository);

    @Test
    void updatesTheBranchName() {
        UUID id = UUID.randomUUID();
        Branch branch = new Branch("Downtown");
        when(branchRepository.findById(id)).thenReturn(Optional.of(branch));
        when(branchRepository.save(branch)).thenReturn(branch);

        Branch updated = useCase.execute(id, "Uptown");

        assertThat(updated.getName()).isEqualTo("Uptown");
    }

    @Test
    void failsWhenTheBranchDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(branchRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(id, "Uptown")).isInstanceOf(BranchNotFoundException.class);
    }
}
