# LucXor options

The config file should be provided to the tool in the following way:

```
java lucxor-{version}.jar luciphor2_input_template.txt
```

Some of the options in the config file are the following:

```
## Input file for Luciphor2 (aka: LucXor).
## Anything after a hash '#' is ignored
## By default, these initial parameters are for performing a phosphorylation search

SPECTRUM_PATH = <fill_in> ## specify the path to the spectra here
SPECTRUM_SUFFIX = MGF     ## available options are MGF, mzML, or mzXML

## Specify your input PSM results format
INPUT_DATA = <fill_in> ## specify the path to the pepXML or tab-delimited file here
INPUT_TYPE = 0   ## 0 = TPP pepXML, 1 = tab-delimited file
ALGORITHM = 0    ## 0 = CID method, 1 = HCD method

TSV_HEADER = 0 ## This pertains ONLY to tab-delimited (TSV) input data
               ## 0 = the input file does NOT contain column names as the first row
               ## 1 = the first row of the input file is the column names

MS2_TOL = 0.5      ## MS/MS fragment ion tolerance
MS2_TOL_UNITS = 0  ## 0 = Daltons, 1 = PPM

MIN_MZ = 150.0 ## do not consider peaks below this value for matching fragment ions

OUTPUT_FILE =  ## Specify the path to your desired output filename here
               ## A default value will be used if nothing is specified here.

WRITE_MATCHED_PEAKS_FILE = 0 ## Generate a tab-delimited file of all the matched peaks
                             ## for the top 2 predictions of each spectra
                             ## Useful for plotting spectra
                             ## 0 = no, 1 = yes

## Place here any FIXED modifications that were used in your search
## This field is ONLY used for tab-delimited input
## Syntax: FIXED_MOD = <RESIDUE> <MODIFICATION_MASS>
## For N-terminal modifications use '[' for the residue character
## For C-terminal modifications use ']' for the residue character
FIXED_MOD = C 57.021464

## Place here any VARIABLE modifications that might be encountered that you don't
## want luciphor to score.
## This field is ONLY used for tab-delimited input
## Syntax: VAR_MOD = <RESIDUE> <MODIFICATION_MASS>
## For N-terminal modifications use '[' for the residue character
## For C-terminal modifications use ']' for the residue character
VAR_MOD = M 15.994915

## List the amino acids to be searched for and their mass modifications
## Syntax: TARGET_MOD = <RESIDUE> <MODIFICATION_MASS>
TARGET_MOD = S 79.966331
TARGET_MOD = T 79.966331
TARGET_MOD = Y 79.966331

## List the types of neutral losses that you want to consider
## The residue field is case sensitive. For example: lower case 'sty' implies
## that the neutral loss can only occur if the specified modification is present
## Syntax: NL = <RESDIUES> -<NEUTRAL_LOSS_MOLECULAR_FORMULA> <MASS_LOST>
#NL = STE -H2O -18.01056    ## a correctly formatted example, (not actually recommended for phospho-searches)
#NL = RKQN -NH3 -17.026548  ## another correctly formatted example, (again not recommended for phospho-searches)
NL = sty -H3PO4 -97.97690

DECOY_MASS = 79.966331  ## how much to add to an amino acid to make it a decoy

## For handling the neutral loss from a decoy sequence.
## The syntax for this is identical to that of the normal neutral losses given
## above except that the residue is always 'X'
## Syntax: DECOY_NL = X -<NEUTRAL_LOSS_MOLECULAR_FORMULA> <MASS_LOST>
DECOY_NL = X -H3PO4 -97.97690

MAX_CHARGE_STATE = 5 ## do not consider PSMs with a charge state above this value
MAX_PEP_LEN = 40 ## restrict scoring to peptides with a length shorter than this value
MAX_NUM_PERM = 16384 ## the maximum number of permutations a sequence can have

SELECTION_METHOD = 0   ## 0 = Peptide Prophet probability (default)
                       ## 1 = Mascot Ion Score
                       ## 2 = -log(E-value)
                       ## 3 = X!Tandem Hyperscore
                       ## 4 = Sequest Xcorr

MODELING_SCORE_THRESHOLD = 0.95 ## Minimum score a PSM needs to be considered for modeling
                                ## The default assumes you are using SELECTION_METHOD=0
                                ## If using SELECTION_METHOD=2 then set this value to your desired e-value it will be converted to -log(e-value) internally
SCORING_THRESHOLD = 0    ## PSMs below this value will be discarded
                         ## Again, if using SELECTION_METHOD=2 then set this value to your desired e-value it will be converted to -log(e-value) internally
MIN_NUM_PSMS_MODEL = 50  ## The minimum number of PSMs you need for any charge state in order to build a model for it

MOD_PEP_REP = 0 ## 0 = show single character modifications, 1 = show TPP-formatted modifications

NUM_THREADS = 0 ## For multi-threading, zero = use all CPU found by JAVA

RUN_MODE = 0 ## Determines how Luciphor will run.
             ## 0 = Default: calculate FLR then rerun scoring without decoys (two iterations)
             ## 1 = Report Decoys: calculate FLR but don't rescore PSMs, all decoy hits will be reported

## This option can be used to help diagnose problems with Luciphor. Multi-threading is disabled in debug mode.
DEBUG_MODE = 0 ## 0 = default: turn off debugging
               ## 1 = write peaks selected for modeling to disk
               ## 2 = write the scores of all permutations for each PSM to disk
               ## 3 = write the matched peaks for the top scoring permutation to disk
               ## 4 = write HCD non-parametric models to disk (HCD-mode only option)
```

## Wrting the config template

Luxifor help you to write a configuration template that the user can edit using the following command:

```
java jar lucxor-{version}.jar -t
```

### Citation:

- Fermin D, Walmsley SJ, Gingras AC, Choi H, Nesvizhskii AI. LuciPHOr: algorithm for phosphorylation site localization with false localization rate estimation using modified target-decoy approach. Mol Cell Proteomics. 2013;12(11):3409‐3419. [doi:10.1074/mcp.M113.028928](https://www.ncbi.nlm.nih.gov/pmc/articles/pmid/23918812/)

- Damian Fermin, Dmitry Avtonomov, Hyungwon Choi, Alexey I. Nesvizhskii, LuciPHOr2: site localization of generic post-translational modifications from tandem mass spectrometry data, Bioinformatics, Volume 31, Issue 7, 1 April 2015, Pages 1141–1143, [https://doi.org/10.1093/bioinformatics/btu788](https://academic.oup.com/bioinformatics/article-pdf/31/7/1141/17125407/btu788.pdf)

