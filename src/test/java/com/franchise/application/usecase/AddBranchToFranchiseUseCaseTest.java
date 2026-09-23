package com.franchise.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.franchise.application.port.out.BranchRepositoryPort;
import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.domain.entity.Branch;
import com.franchise.domain.entity.Franchise;
import com.franchise.domain.exception.FranchiseNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class AddBranchToFranchiseUseCaseTest {

    private final FranchiseRepositoryPort franchiseRepository = mock(FranchiseRepositoryPort.class);
    private final BranchRepositoryPort branchRepository = mock(BranchRepositoryPort.class);
    private final AddBranchToFranchiseUseCase useCase = new AddBranchToFranchiseUseCase(franchiseRepository, branchRepository);

    @Test
    void addsABranchToAnExistingFranchise() {
        UUID franchiseId = UUID.randomUUID();
        Franchise franchise = new Franchise("Fast Burger");
        when(franchiseRepository.findById(franchiseId)).thenReturn(Optional.of(franchise));
        when(branchRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Branch branch = useCase.execute(franchiseId, "Downtown");

        assertThat(branch.getName()).isEqualTo("Downtown");
        assertThat(branch.getFranchise()).isEqualTo(franchise);
        assertThat(franchise.getBranches()).containsExactly(branch);
    }

    @Test
    void failsWhenTheFranchiseDoesNotExist() {
        UUID franchiseId = UUID.randomUUID();
        when(franchiseRepository.findById(franchiseId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(franchiseId, "Downtown")).isInstanceOf(FranchiseNotFoundException.class);
    }
}
