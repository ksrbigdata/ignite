/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.igfs;

import org.apache.ignite.igfs.IgfsFile;
import org.apache.ignite.igfs.IgfsPath;
import org.apache.ignite.igfs.secondary.IgfsSecondaryFileSystem;

import java.io.OutputStream;
import java.util.Map;

/**
 * Context for secondary file system create request.
 */
public class IgfsSecondaryFileSystemCreateContext {
    /** File system. */
    private final IgfsSecondaryFileSystem fs;

    /** Simple create flag. */
    private final boolean simpleCreate;

    /** Replication. */
    private final short replication;

    /** Block size. */
    private final long blockSize;

    /** Buffer size. */
    private final int bufSize;

    /**
     * Constructor.
     *
     * @param fs File system.
     * @param simpleCreate Simple create flag.
     * @param blockSize Block size.
     * @param replication Replication.
     */
    public IgfsSecondaryFileSystemCreateContext(IgfsSecondaryFileSystem fs, boolean simpleCreate, short replication,
        long blockSize, int bufSize) {
        this.fs = fs;
        this.simpleCreate = simpleCreate;
        this.replication = replication;
        this.blockSize = blockSize;
        this.bufSize = bufSize;
    }

    /**
     * Create file in the secondary file system.
     *
     * @param path Path.
     * @param overwrite Overwrite flag.
     * @param props Properties.
     * @return Output stream.
     */
    public OutputStream create(IgfsPath path, boolean overwrite, Map<String, String> props) {
        return simpleCreate ? fs.create(path, overwrite) :
            fs.create(path, bufSize, overwrite, replication, blockSize, props);
    }

    /**
     * Get file info.
     *
     * @param path Path.
     * @return File.
     */
    public IgfsFile info(IgfsPath path) {
        return fs.info(path);
    }

    /**
     * @return Secondary file system.
     */
    public IgfsSecondaryFileSystem fileSystem() {
        return fs;
    }

    /**
     * @return Simple crate flag.
     */
    public boolean simpleCreate() {
        return simpleCreate;
    }

    /**
     * @return Replication.
     */
    public short replication() {
        return replication;
    }

    /**
     * @return Block size.
     */
    public long blockSize() {
        return blockSize;
    }

    /**
     * @return Buffer size.
     */
    public int bufferSize() {
        return bufSize;
    }
}
