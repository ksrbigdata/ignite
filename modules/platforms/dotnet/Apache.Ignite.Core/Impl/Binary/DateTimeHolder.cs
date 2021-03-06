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

namespace Apache.Ignite.Core.Impl.Binary
{
    using System;
    using System.Diagnostics;
    using Apache.Ignite.Core.Binary;

    /// <summary>
    /// Wraps DateTime item in a binarizable.
    /// </summary>
    internal class DateTimeHolder : IBinaryWriteAware
    {
        /** */
        private readonly DateTime _item;

        /// <summary>
        /// Constructor.
        /// </summary>
        /// <param name="item">The item to wrap.</param>
        public DateTimeHolder(DateTime item)
        {
            _item = item;
        }

        /// <summary>
        /// Constructor.
        /// </summary>
        /// <param name="reader">The reader.</param>
        public DateTimeHolder(IBinaryReader reader)
        {
            Debug.Assert(reader != null);

            _item = DateTime.FromBinary(reader.GetRawReader().ReadLong());
        }

        /// <summary>
        /// Gets the item to wrap.
        /// </summary>
        public DateTime Item
        {
            get { return _item; }
        }

        /** <inheritDoc /> */
        public void WriteBinary(IBinaryWriter writer)
        {
            Debug.Assert(writer != null);

            writer.GetRawWriter().WriteLong(_item.ToBinary());
        }
    }
}