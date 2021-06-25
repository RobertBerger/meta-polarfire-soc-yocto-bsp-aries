1) create a new repo on github

git clone git@github.com:RobertBerger/meta-polarfire-soc-yocto-bsp-aries.git

2) add my-scripts dir

cd meta-polarfire-soc-yocto-bsp-aries

echo "# meta-polarfire-soc-yocto-bsp fork" >> README.md

git add .

git commit -m "first commit"

git push -u origin master

3) backup my repo, so I can edit this file

cp -r meta-riscv mets-riscv.ori

4) add upstream

cd meta-polarfire-soc-yocto-bsp-aries

git remote add official-upstream git://github.com/ARIES-Embedded/meta-polarfire-soc-yocto-bsp.git

git fetch official-upstream

warning: no common commits
remote: Enumerating objects: 2433, done.
remote: Counting objects: 100% (2433/2433), done.
remote: Compressing objects: 100% (1049/1049), done.
remote: Total 2433 (delta 1266), reused 2354 (delta 1188), pack-reused 0
Receiving objects: 100% (2433/2433), 24.41 MiB | 221.00 KiB/s, done.
Resolving deltas: 100% (1266/1266), done.
From git://github.com/ARIES-Embedded/meta-polarfire-soc-yocto-bsp
 * [new branch]      aries      -> official-upstream/aries
 * [new branch]      master     -> official-upstream/master
 * [new tag]         v2021.04   -> v2021.04
 * [new tag]         2020.10    -> 2020.10
 * [new tag]         2020.11    -> 2020.11
 * [new tag]         v2020.05   -> v2020.05
 * [new tag]         v2021.02   -> v2021.02

git branch -a

* master
  remotes/official-upstream/aries
  remotes/official-upstream/master
  remotes/origin/master

5) use specific upstream branch/commit and make own branch

syntax: git fetch url-to-repo branchname:refs/remotes/origin/branchname

git fetch git://github.com/ARIES-Embedded/meta-polarfire-soc-yocto-bsp.git aries:refs/remotes/origin/aries

From git://github.com/ARIES-Embedded/meta-polarfire-soc-yocto-bsp
 * [new branch]      aries      -> origin/aries

git branch -a
* master
  remotes/official-upstream/aries
  remotes/official-upstream/master
  remotes/origin/HEAD -> origin/master
  remotes/origin/aries
  remotes/origin/master

6) Update from upstream:
git co master
>> git remote -v

official-upstream       git://github.com/ARIES-Embedded/meta-polarfire-soc-yocto-bsp.git (fetch)
official-upstream       git://github.com/ARIES-Embedded/meta-polarfire-soc-yocto-bsp.git (push)
origin  git@github.com:RobertBerger/meta-polarfire-soc-yocto-bsp-aries.git (fetch)
origin  git@github.com:RobertBerger/meta-polarfire-soc-yocto-bsp-aries.git (push)

>> git fetch official-upstream

---

7) My own branch:

git co master
git co official-upstream/aries
git checkout -b 2021-06-25-hardknott-aries
git checkout -b 2021-06-25-hardknott-aries-res
git co master
cd my-scripts
./push-all-to-github.sh

8) apply patches

stg init

stg series

stg import --mail ../meta-virtualization-patches/2019-09-09-warrior-2.7+/0001-use-systemd-as-cgroup-manager-for-docker-While-it-s-.patch

import all patches

...

stg series

stg commit --all

git log

git co master

9) push to my upstream

my-scripts/push-all-to-github.sh

