# Stanford CoreNLP

The default Relation Extractor classes that are compatible with training data formatted in the same
way as Roth and Yih's data, as per the format described in http://cogcomp.cs.illinois.edu/page/resource_view/43.

More details are in http://nlp.stanford.edu/software/relationExtractor.html

#FAQ

#### Why you modified CoreNLP?

Please note the following classes inside this repository:
    `edu/stanford/nlp/ie/machinereading/domains/roth/RothCONLL04Reader.java`
    `edu/stanford/nlp/ie/machinereading/domains/roth/RothEntityExtractor.java`

They contain hard coded list of possible entities in the input training data,
as per the issue described in here: https://github.com/stanfordnlp/CoreNLP/issues/359

This repository simply adds other entities I needed for my research on Information/Relation Extraction
from academic papers.


#### What is your research?

With Dr. Wei Wang from UNSW as my supervisor, I built an Information Extraction tool, focused in
extracting information from academic papers as to build knowledge graphs.

More info coming soon!


#### Why this is not a fork?

Note this repository contains the source code for the 3.6.0 release of the CoreNLP. Moreover, it's unclear from the
commits in https://github.com/stanfordnlp/CoreNLP what is the version in there
that matches 3.6.0, since releases are not created from their public repository. I then
re-created the repository based on the content from `stanford-corenlp-3.6.0-sources.jar` file, part of the
distribution.


#### Compiling

Please check https://github.com/stanfordnlp/CoreNLP#build-instructions


# License

LICENSE as per https://github.com/stanfordnlp/CoreNLP/blob/master/LICENSE.txt
