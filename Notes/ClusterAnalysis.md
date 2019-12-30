# Cluster Analysis

Extracting Information from unlabelled, untrained data

- nice to see visually
- really good for anomally detection (spot outliers)

## Data Matrix

.csv fie

An object-by-variable structure with n objects and p variables/attributes that form a relational table

## Dissimilarity Matrix 

- Mark refers to this as _Similarity Matrix_ - measure of object by similarity

object-by-variable structure, stores collection of proximities that are available for all pairs of _n_ objects. Represented by an _n_ x _n_ table

- represents _n_ objects

- d(i,j) represents the measured **difference**/dissimilarity between objects i and j.
- d(i,i) = 0; they are the same, completely similar

rows and columns of data matrix represnt different entities, while dissimilarity matrix represent the same entities

Many clustering algorithms operate on dissimilarity matrix

**Interval-scaled** variables = continuous measurements of roughly linear scale. Weight/Height, Latitude/Longitude co-ords, weather temp etc

data should be **standardised** to give all variables an _equal weight_. Useful when no prior knowledge of data

- convert original measurements to **unitless** variables. Original scale out the window.
    1. Calculate **mean absolute deviation** - more robust to outliers than standard deviation. Deviations from the mean are _not squared_
    2. Calculate the **z score** (standardised measurements)
                    
        Z<sub>if</sub> = (x<sub>if</sub> - m<sub>f</sub>) / s<sub>f</sub>

    - median absolute deviation is more robust again, but outliers can still be noted using mean absolute deviation

**Euclidean Distance**

After standardization, dissimilarity between the objects described by Interval Scaled vars is typically computed based on distance between each pair of objects

- **Manhattan Dist** another well known metric you could use. No squaring!

Both distances satisfy the following:
- distance non-negative
- dist of object to itself = 0
- Distance is a symmetric function (ab = ba)
- Triangular inequality.

**Binary Variable** two states: 0 and 1. 

- treating binary vars as interval scaled leads to misleading clustering results - _don't treat them as numbers_
- 2-by-2 contingency table where:
    - q = 0,0
    - r = 0,1
    - s = 1,0
    - t = 1,1
    - ... 

![contigency table](./Resources/contigency-table.png)

- binary var is symmetric if both its states are equally valuable and carry the same weigh: no preference on which outcome 

        d(i, j) = (r + s) / (q + r + s + t)
    
- asymmetric if outcomes of the states are not equally important. Most important (rarest) as 1, other by 0.
    - in asymmetric, value t is considered unimportant and discarded.

             d(i, j) = (r + s) / (q + r + s)

**Jaccard coefficient** coefficient sim(i,j) where

![Jaccard calculation](./Resources/jaccard.png)

### Categorical Variable

A generalization of the binary variable, but it can take on more than two states.

- let M = no. of possible states. States can be denoted by letters, symbols or set of integers
- Dissimilarity between i and j can be computed based on the **ratio of mismatches** 
    - Weights can be assigned to increase effect of m of to assign greater weight to specific states

![dissimilarity for categorical](./Resources/dissim-cat.png)

- m = number of matches (no of variables for which i and j have the same state)
- p = total no of variables

**k-means algorithm**
- an _exclusive clustering algorithm_
- randomly select k of the objects, each initially represents a cluster mean or center - the **centroid**
- each remaining obj then assigned to the cluster to which it is most similar, based on the distance between the object and the cluster mean.
- Then compute the new mean for each cluster.
- process iterates until 'we no longer move'

Compute an **error** for each cluster. Clusters that change less between each iteration of the k-means algorithm have a lower error function.

**Square-error Criterion**

![Square-error criterion](./Resources/sec.png)

- E is the sum of the square error for all objects in the dataset
- p is the point in space in space representing a given obj (multidimensional)
- m<sub>i</sub> is the mean of cluster C<sub>i</sub> (multidimensional)

For every object in each cluster, the dist from the object to its cluster center is squared and the distances are summed

**Centroid** - "the point for which each attribute value is the average of the values of the corresponding attribute for all the points in the cluster."

Centroid will sometimes be one of the points in the cluster, but frequently is an imaginary point (not a part of the cluster itself)

**SUMMARY**

Basic clustering Problem is simple: wish to distribute objects into goups in which objs within each group are similar whereas groups themeslves are different.

k- means algorithm always terminates, but doesn't necessarily find the best clusters. Set of clusters with the smallest

### k-Means Clustering

- Each object assigned to only one of a set of clusters. exclusive
- Begin by deciding how many clusters to form from the data. This is k
- Can do 
    - measure the quality of a set of clusters using the val of an objective function which we will take to be the sum of the squares of the distances of each point from the centroid of the cluster to which it is assigned - this should be as small as possible
    - select _k_ points to be treated as the centroid of _k_ potential clusters. Best if points far apart from each other are selected
    - Assign each of the points one by one to the cluster which has the nearest centroid.
    - once all objects have been assigned, we have k clusters but the original centroids will no longer be the true centroids. Recalculate the centroids and repeat the assignment of each point
    - Repeat until the centroids no longer move. These are the final clusters by the alg for the _initial choice of centroids_

K-means will always terminate, but doesn't necessarily find the best set of clusters. Depends on initial selection of centroids. Need to minimise the value of the **objective function**

### k-Medioids

k-medioids diminishes sensitivity to outliers present in k-means

Pick an _actual object_ to represent the clusters rather than the mean value. Each remaining object is clustered with the representative object to which it is most similar.

Use an **absolute-error criterion** similar to the square-error criterion for k-means.

![Absoltute-Error criterion](./Resources/abs-error.png)

- E is the sum of the absolute error for all objects in the dataset
- p is the point in space representing a given object in cluster C<sub>j</sub>
- o<sub>j</sub> is the representative object of C<sub>j</sub>.

Algorithm iterates until each representative object is actually the **medioid**, or most centrally located object of its cluster. Initial representative objects are chosen arbitrarily. 

These are then replaced by non-representative objects if the quality of the resulting clustering is improved. Calculated using a cost function that measures the average dissimilarity between an obj and the rep obj of its cluster

**Reassigning Representative Object**

Is o<sub>random</sub> a good replacement for o(j)?

- p currently belongs to rep obj o<sub>j</sub>. if o<sub>j</sub> replaced by o<sub>random</sub> as a rep obj and p is closest to a different rep obj o<sub>i</sub> (i != j), then _p is reassigned to o<sub>i</sub>._
- p currently belongs to rep obj o<sub>j</sub>. If o<sub>j</sub> replaced by o<sub>random</sub> as a rep obj and p is closest to o<sub>random</sub>, then _p reassigned to o<sub>random</sub>_.
- p currently belongs to rep obj o<sub>j</sub>. If o<sub>j</sub> replaced by o<sub>random</sub> as a rep obj and p is still closest to o<sub>i</sub>, then assignment does not change.
- p currently belongs to rep obj o<sub>i</sub>, i  != j. If o<sub>j</sub> is replaced by o<sub>random</sub> as a rep obj and p is closest to o<sub>random</sub>, then _p is reassigned to o<sub>random</sub>_

![Cost function for k-medioids clustering.](./Resources/k-medioid-costs.png) 

Each time a reassignment occurs, a difference in the absolute error E is contributed to cost function

- cost function calculates diff in absolute error value if current representative object is replaced by a non-representative object
- total cost of swapping = sum of costs incurred by all non-rep objs
    - if negative, then o<sub>j</sub> is replaced
    - if positive, try different r obj

**PAM - Partitioning Around Medioids** - determines k partitions for n objects

- choose k (no of clusters) objects in D (dataset) as initial rep 
- assign each remaining obj
- randomly select nonrepresentative obj
- compute total cost S of swapping rep obj with random
- if S < 0 then  swap o(j) with o(random), if not reselect random obj

after an initial random selection of k objects, algo repeatedly tries to make a better choice of cluster reps
- all possible pairs are analysed, where one object in each pair is considered a rep object and the other is not

k-medioids is more robust than k-means. Less noise n outliers, but processing is more costly...

**AHC: Agglomerative Hierarchical Clustering**

Start with each object in a cluster of its own, then repeatedly merge closest cluster pairs until just one cluster containing all remains.
- assign each obj to single obj cluster. Calculate distance
- choose closest pair of clusters and merge into one
- calculate distance between the new cluster and each of the og clusters
- repeat 2 and 3 until one all encompassing cluster remains

if N objects, there will be N-1 mergers.

This generates hierarchy of clusters.

**Dendogram** a binary tree to represent this hierarchical clustering process
- 'root' = single cluster at end.
- leaf nodes are the original single-object clusters.

**Distance Matrix**: generated and maintained to record every pair of clusters for each pass, leabing distances not involved in the most recent merger unchanged.

- Table is symmetric, so not all values need to be calculated (e.g a to f == f to a). 
- we take the closest pair of clusters and _combine_ these into a single cluster eg a dn d below

![Initial Distance Matrix](./Resources/dm-1.png)

a and d have the shortest distance, 3...

![New Distance Matrix](./Resources/dm-2.png)

At this point, we could calculate centroid etc, but we use **single link** clustering to use less calculation. The shortest distance from any member of one cluster to any member of the other is used as the distance between the clusters

(Other options incl complete-link and average-link clustering, where longest distance between any members or the average distance is used respectively)

![After Merger](./Resources/dm-3.png)

### Termination

Usually satisfied to allow clustering algorithm to produce a complete cluster hierarchy

May prefer to end once we have converted original N objects into a _small enough_ set of clusters

- can merge clusters until nly some pre-defined numbers remain
- can stop merging when a newly created cluster fils to meet some criterion for its compactness (eg average dist between clusters is too high)

### Outliers

One mans noise is another man's signal.. outliers may be of interest eg to monitor fraud. Many data mining algorithms try to minimize influence of outliers, but others ry outlier mining specifically

Outlier Mining
- define what data could be considered inconsistent in a given dataset
- find an efficient method to mine the outliers as defined

Definition of outliers depends on categoric/numeric, no of dimensions in which a point has extreme values, hidden in time-series trends etc

**Statistical Distribution-Based Outlier Detection**

Assumes a probability model for the given data set, then identifies outliers with respect to the model using a discordancy test.

Discordancy test examines two hypotheses: a working (null) hypothesis and an alternative hypothesis

P-value helps to determine significance of results

- small p-value (<= 0.5) indicates strong evidence against the null hypothesis, so reject the null
- large indicates weak evidence against null, so we fail to reject the null
- p-values v close to cutoff are considered inconclusive

Discordancy test verifies whether an object o<sub>i</sub> is significantly large/small in relation to distribution F.

Significance probability:
            
        SP(v)=Prob(T > v)

        T = some statistic
        v = value for object o

        if SP(v) is sufficiently small, then o is discordant and the working hypothesis is rejected

(Case study: taxi company)

- **Block Procedures**: either all the suspect objects are treated as outliers or all of them accepted as consistent

- **Sequential Procedures**: eg the _inside out procedure_. Main idea is that object least likely to be an outlier is tested first. Once an outlier is encountered, all the other more extreme values also considered outliers. More effective

Big drawback of statistical approach is that most tests are for single attributes, whereas many data mining problems require finding outliers in multidimensional space. Requires knowledge about the parameters of a dataset. Do not guarantee all outliers will be found for the cases where no specific test was developed.
