package com.igorwojda.puzzletest

import com.igorwojda.puzzletest.utils.PuzzleUtils
import com.igorwojda.puzzletest.utils.SolutionFile
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path

class ProjectConsistencyTest {

    @ParameterizedTest(name = "Puzzle file exists: {0}")
    @MethodSource("getPuzzleRequiredFilePaths")
    fun `Puzzle file exists`(puzzleFilePath: String) {
        val path = Path(puzzleFilePath)
        require(Files.exists(path)) { "Missing file $path" }
    }

    companion object {
        @JvmStatic
        fun getPuzzleRequiredFilePaths() = PuzzleUtils
            .getPuzzleDirectories()
            .flatMap {
                getProjectRequiredFiles(it)
            }

        private fun getProjectRequiredFiles(puzzleDirectory: File) = SolutionFile
            .values()
            .map {
                "${puzzleDirectory.path}/${it.fileName}"
            }
    }
}