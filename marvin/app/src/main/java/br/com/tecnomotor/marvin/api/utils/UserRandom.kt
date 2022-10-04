package br.com.tecnomotor.marvin.api.utils

import br.com.tecnomotor.marvin.api.model.MobileUser

class UserRandom {
    fun generatedUserRandom(): MobileUser {
        val listCodes = arrayListOf(
            "Vy5NCBdiHu7pH6kPZ4xDMj7wJ7JVZwk63kW3t4GF",
            "6sN3yZBf7z58RKBpzy6G36GZV9B82MLe9Db23T88",
            "edvCxtJn435f8HqSU4u9m2JD2u9qcjexRr5VWzvf",
            "FEiT5b5f8N9amGVU5hA3oTFzM3p62f3JdfqiR94y",
            "cWSW3jz437CzXWVpFn8MR37az9W9S4PbHGZ3Ssda",
            "Sfh5Cx9K3ckTHSUx5zn5wv74w2wWevq86mLEHCJw",
            "8qK7B7ekt6Yre4dSbXM56oSuv25JZ3EGg7foyH57",
            "PgLGk8cVm74ZydmYS627ScG6JDXv7X9SYK69c2tW",
            "5gDFPFK4DLoH28Wwi8N3956N8bLx9c3M5ZtzyPua",
            "mZBTU69gKk5D38Gj8668p95dfo9LpFpqr6HfSBz7",
            "spE9U6GpU95A5hbezvdu549z4eMgr2Q6NE9xP4BB",
            "Xj5yiXR6L3r23xPJkLsws432xr6Q7F8T38h2DPKv",
            "m4U8V3r6AMBn9NpeK8K4368x49ETNXKQVxEjBh67",
            "oVx26tpUAmggyQaKvsQ9kE4Cne2k4Cg828cmJEfw",
            "Ncceokog7t8p2378ZQ7f66fCsm4P4Jx2y64rV43n",
            "Y23Q5zdfCsXqdYXzisaPKBApn9pnA7ME3772s74H",
            "bmMwMMKGL8Ch785P7p5DEZDbSSeh4a8sE5X7b7x7",
            "jf952Vvo4N88mfV5S7wuZ5NrEGdZqeg43U6CR9u9",
            "owp2n5Ni6WQ3z8x94gy74Ekrkne6dhJ4bYyAeH9u",
            "XvX3LW7rs8XWkK34xDM6Ceopn4SpeCH8N627jAfx",
            "Z64ezT57qY73G4yXn9TXR5t89fgQcpAhA83A94MU",
            "mgRM839UDw3jfsy28RFWb3wEkN784kv4epKS2L6W",
            "i42V2845Z898FEF4z7sHkhoNREEU2ibyY5MYw9Si",
            "666yVmjqW4miwmq8jYz274LbuqtS437LsmbvFdT2",
            "MuC6v7avrQ88x6f44kEm8z86LYLUkzqCLw2r25Bn",
            "YFT7sV4UxtJ58nNWk6F45M34Djv9Ap68N684E9K2",
            "ozKx3bG5VY4f93KzFKRqW85k2R2cng37L5RfLyG2",
            "6R9b33U2844Mj3Ury9HFZdb8HpFyn8hk29g338gu",
            "Tw9W6E662RqjWu64sBrpu75wLFTCA6azx4376y5N",
            "8pywywWc6BD3f6f4TcpJ929vTMDM3sb2kiPwgU4f",
            "jXcXm88y46H3mQHd4x7iB6s79496D4uxHKxQGAQ9",
            "2j3soVhRVBw8T24JPq9Z7L5NHPjtugxgb6V9vf7D",
            "HsE73fW6c35vKnLn7LdiDBNecT96nNdUk3uVDy87",
            "Dk55oGT3p5aw85278x76p9GrgFKkbEnEenSo3d9J",
            "s37X32VSDjjh8StaMqrw9R2FiED6uvNxE348JE4u",
            "99A7WWaP3K3g8CUQeTQ667yU2s5n9Gb4Vf48R52w",
            "4k2Bu7sVDQ8Q4dtEZNB6o9WFsseYC9z89an26iHP",
            "66fE7NKYCTjA48d6Nzae742gXcob84563yj7E4uQ",
            "Ayj3YHn77TdR5H4mpFXS34YY9UtuPQ97v3CWgL2m",
            "6555ZuNQpNZh4RfBo662azwd9U5cuhA472iciuAy",
            "RHi3D5LTLXsGk4uDfF3D4H7P2V94B6DeRSF2Xq53",
        )
        val string = listCodes.random()
        return MobileUser(string, string)
    }
}